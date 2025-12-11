package com.example.casestudy.data.remote.socket

import android.util.Log
import com.example.casestudy.data.mapper.toDomain
import com.example.casestudy.data.remote.api.ApiService
import com.example.casestudy.data.remote.dto.order.OrderDto
import com.example.casestudy.data.remote.dto.socket.SocketAuthRequest
import com.example.casestudy.domain.model.Order
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderSocketService @Inject constructor(
    private val gson: Gson,
    private val client: OkHttpClient,
    private val api: ApiService
) {

    private val channel = Channel<Order>(Channel.BUFFERED)
    val orderFlow = channel.receiveAsFlow()

    private var webSocket: WebSocket? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val host = com.example.casestudy.BuildConfig.PUSHER_HOST
    private val port = com.example.casestudy.BuildConfig.PUSHER_PORT
    private val appKey = com.example.casestudy.BuildConfig.PUSHER_KEY
    
    private var currentSocketId: String? = null
    private var currentRestaurantId: Int? = null

    fun connect(token: String, restaurantId: Int) {
        if (webSocket != null) {
            close()
        }
        currentRestaurantId = restaurantId

        val url = "ws://$host:$port/app/$appKey?protocol=7&client=js&version=4.3.1&flash=false"
        
        val request = Request.Builder()
            .url(url)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("Socket", "Connected to Pusher")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("Socket", "Message: $text")
                handleMessage(webSocket, text)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("Socket", "Closing: $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("Socket", "Error", t)
            }
        })
    }

    private fun handleMessage(socket: WebSocket, text: String) {
        try {
            val json = JSONObject(text)
            val event = json.optString("event")
            val dataStr = json.optString("data")

            when (event) {
                "pusher:connection_established" -> {
                    val dataJson = JSONObject(dataStr)
                    currentSocketId = dataJson.optString("socket_id")
                    subscribeToChannel(socket)
                }
                "order.created" -> {
                    val orderDto = gson.fromJson(dataStr, OrderDto::class.java)
                    channel.trySend(orderDto.toDomain())
                }
                "pusher:ping" -> {
                    socket.send("{\"event\":\"pusher:pong\"}")
                }
            }
        } catch (e: Exception) {
            Log.e("Socket", "Parse error", e)
        }
    }

    private fun subscribeToChannel(socket: WebSocket) {
        val socketId = currentSocketId ?: return
        val restaurantId = currentRestaurantId ?: return
        val channelName = "private-restaurant.$restaurantId"

        scope.launch {
            try {
                val authResponse = api.socketAuth(
                    SocketAuthRequest(
                        socket_id = socketId,
                        channel_name = channelName
                    )
                )

                val subscribeData = JSONObject().apply {
                    put("channel", channelName)
                    put("auth", authResponse.auth)
                }

                val subscribeEvent = JSONObject().apply {
                    put("event", "pusher:subscribe")
                    put("data", subscribeData)
                }

                socket.send(subscribeEvent.toString())
                Log.d("Socket", "Subscribing to $channelName")

            } catch (e: Exception) {
                Log.e("Socket", "Auth failed", e)
            }
        }
    }

    fun close() {
        webSocket?.close(1000, null)
    }
}
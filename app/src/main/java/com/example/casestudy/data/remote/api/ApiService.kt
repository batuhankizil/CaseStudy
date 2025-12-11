package com.example.casestudy.data.remote.api

import com.example.casestudy.data.remote.dto.auth.AuthResponse
import com.example.casestudy.data.remote.dto.auth.LoginRequest
import com.example.casestudy.data.remote.dto.auth.RegisterRequest
import com.example.casestudy.data.remote.dto.common.BaseResponse
import com.example.casestudy.data.remote.dto.order.OrderDto
import com.example.casestudy.data.remote.dto.order.OrderStatusRequest
import com.example.casestudy.data.remote.dto.restaurant.RestaurantCreateRequest
import com.example.casestudy.data.remote.dto.restaurant.RestaurantResponse
import com.example.casestudy.data.remote.dto.socket.SocketAuthRequest
import com.example.casestudy.data.remote.dto.socket.SocketAuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("v1/customer/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): AuthResponse

    @POST("v1/customer/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("v1/customer/restaurant")
    suspend fun createRestaurant(
        @Body body: RestaurantCreateRequest
    ): BaseResponse<RestaurantResponse>

    @GET("v1/customer/restaurant")
    suspend fun getRestaurantInfo(): BaseResponse<List<RestaurantResponse>>

    @GET("v1/order/get_order_list")
    suspend fun getOrderList(): BaseResponse<List<OrderDto>>

    @POST("v1/order/accept_or_cancel_order")
    suspend fun updateOrderStatus(
        @Body body: OrderStatusRequest
    ): BaseResponse<Unit>

    @POST("broadcasting/auth")
    suspend fun socketAuth(
        @Body body: SocketAuthRequest
    ): SocketAuthResponse
}
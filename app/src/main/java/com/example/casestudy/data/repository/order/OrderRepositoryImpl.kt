package com.example.casestudy.data.repository.order

import com.example.casestudy.data.mapper.toDomain
import com.example.casestudy.data.remote.api.ApiService
import com.example.casestudy.data.remote.dto.order.OrderStatusRequest
import com.example.casestudy.domain.model.Order
import com.example.casestudy.domain.repository.order.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: ApiService
) : OrderRepository {

    companion object {
        private val cachedOrders = mutableListOf<Order>()
        private const val TAG = "OrderRepo"
    }

    override suspend fun getOrders(): List<Order> {
        return try {
            val response = api.getOrderList()
            val remoteOrders = response.data?.map { it.toDomain() }.orEmpty()
            
            android.util.Log.d(TAG, "API returned ${remoteOrders.size} orders")

            synchronized(cachedOrders) {
                for (remoteOrder in remoteOrders) {
                    val index = cachedOrders.indexOfFirst { it.uniqueCode == remoteOrder.uniqueCode }
                    if (index != -1) {
                         cachedOrders[index] = remoteOrder
                    } else {
                        cachedOrders.add(remoteOrder)
                    }
                }

                cachedOrders.sortByDescending { it.createdAt }
                
                android.util.Log.d(TAG, "Returning ${cachedOrders.size} cached orders")
                cachedOrders.toList()
            }
        } catch (e: Exception) {
            android.util.Log.e(TAG, "API failed", e)
            if (cachedOrders.isNotEmpty()) {
                cachedOrders.toList()
            } else {
                throw e
            }
        }
    }

    override suspend fun updateOrderStatus(uniqueCode: String, status: String): Boolean {
        return try {
            val response = api.updateOrderStatus(OrderStatusRequest(uniqueCode, status))
            if (response.success) {
                 synchronized(cachedOrders) {
                    val index = cachedOrders.indexOfFirst { it.uniqueCode == uniqueCode }
                    if (index != -1) {
                        val currentOrder = cachedOrders[index]
                        cachedOrders[index] = currentOrder.copy(
                             orderDetails = currentOrder.orderDetails.copy(status = status)
                        )
                        android.util.Log.d(TAG, "Updated order $uniqueCode to $status in cache")
                    }
                }
            }
            response.success
        } catch (e: retrofit2.HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val message = if (errorBody != null) {
                try {
                    val json = org.json.JSONObject(errorBody)
                     json.optString("message") + ": " + json.optString("error")
                } catch (jsonException: Exception) {
                    "Unknown server error"
                }
            } else {
                e.message()
            }
            throw Exception(message)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun cacheOrder(order: Order) {
        synchronized(cachedOrders) {
            val index = cachedOrders.indexOfFirst { it.uniqueCode == order.uniqueCode }
            if (index != -1) {
                cachedOrders[index] = order
            } else {
                cachedOrders.add(0, order)
            }
            android.util.Log.d(TAG, "Manually cached order ${order.uniqueCode}")
        }
    }
}
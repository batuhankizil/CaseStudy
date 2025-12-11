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

    override suspend fun getOrders(): List<Order> {
        val response = api.getOrderList()
        return response.data?.map { it.toDomain() }.orEmpty()
    }

    override suspend fun updateOrderStatus(uniqueCode: String, status: String): Boolean {
        return try {
            val response = api.updateOrderStatus(OrderStatusRequest(uniqueCode, status))
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
}
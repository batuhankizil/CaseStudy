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
        val response = api.updateOrderStatus(OrderStatusRequest(uniqueCode, status))
        return response.success
    }
}
package com.example.casestudy.domain.repository.order

import com.example.casestudy.domain.model.Order

interface OrderRepository {
    suspend fun getOrders(): List<Order>
    suspend fun updateOrderStatus(uniqueCode: String, status: String): Boolean
    suspend fun cacheOrder(order: Order)
}
package com.example.casestudy.domain.usecase.order

import com.example.casestudy.domain.model.Order
import com.example.casestudy.domain.repository.order.OrderRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val repo: OrderRepository
) {
    suspend operator fun invoke(): Result<List<Order>> {
        return try {
            val orders = repo.getOrders()
            Result.success(orders)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
package com.example.casestudy.domain.usecase.order

import com.example.casestudy.domain.repository.order.OrderRepository
import javax.inject.Inject

class UpdateOrderStatusUseCase @Inject constructor(
    private val repo: OrderRepository
) {
    suspend operator fun invoke(uniqueCode: String, status: String): Result<Boolean> {
        return try {
            val result = repo.updateOrderStatus(uniqueCode, status)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
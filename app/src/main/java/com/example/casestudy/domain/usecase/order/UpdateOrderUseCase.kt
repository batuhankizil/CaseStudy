package com.example.casestudy.domain.usecase.order

import com.example.casestudy.domain.repository.order.OrderRepository
import javax.inject.Inject

class UpdateOrderStatusUseCase @Inject constructor(
    private val repo: OrderRepository
) {
    suspend operator fun invoke(uniqueCode: String, status: String): Result<Boolean> {
        return try {
            val success = repo.updateOrderStatus(uniqueCode, status)
            if (success) {
                Result.success(true)
            } else {
                Result.failure(Exception("Failed to update status"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
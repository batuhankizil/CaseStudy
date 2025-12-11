package com.example.casestudy.domain.usecase.order

import com.example.casestudy.domain.model.Order
import com.example.casestudy.domain.repository.order.OrderRepository
import javax.inject.Inject

class CacheOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(order: Order) {
        repository.cacheOrder(order)
    }
}

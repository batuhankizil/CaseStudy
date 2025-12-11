package com.example.casestudy.domain.usecase.restaurant

import com.example.casestudy.domain.model.Restaurant
import com.example.casestudy.domain.repository.restaurant.RestaurantRepository
import javax.inject.Inject

class GetRestaurantUseCase @Inject constructor(
    private val repo: RestaurantRepository
) {
    suspend operator fun invoke(): Result<Restaurant?> {
        return try {
            val restaurant = repo.getRestaurant()
            Result.success(restaurant)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
package com.example.casestudy.domain.usecase.restaurant

import com.example.casestudy.data.remote.dto.restaurant.RestaurantCreateRequest
import com.example.casestudy.domain.model.Restaurant
import com.example.casestudy.domain.repository.restaurant.RestaurantRepository
import javax.inject.Inject

class CreateRestaurantUseCase @Inject constructor(
    private val repo: RestaurantRepository
) {
    suspend operator fun invoke(body: RestaurantCreateRequest): Result<Restaurant?> {
        return try {
            val restaurant = repo.createRestaurant(body)
            Result.success(restaurant)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
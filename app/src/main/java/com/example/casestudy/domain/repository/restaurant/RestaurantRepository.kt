package com.example.casestudy.domain.repository.restaurant

import com.example.casestudy.data.remote.dto.restaurant.RestaurantCreateRequest
import com.example.casestudy.domain.model.Restaurant

interface RestaurantRepository {

    suspend fun createRestaurant(body: RestaurantCreateRequest): Restaurant?
    suspend fun getRestaurant(): Restaurant?
}

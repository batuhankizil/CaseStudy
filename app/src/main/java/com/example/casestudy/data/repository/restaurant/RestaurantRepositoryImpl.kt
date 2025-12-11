package com.example.casestudy.data.repository.restaurant

import com.example.casestudy.data.mapper.toDomain
import com.example.casestudy.data.remote.api.ApiService
import com.example.casestudy.data.remote.dto.restaurant.RestaurantCreateRequest
import com.example.casestudy.domain.model.Restaurant
import com.example.casestudy.domain.repository.restaurant.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: ApiService

) : RestaurantRepository {

    override suspend fun createRestaurant(
        body: RestaurantCreateRequest
    ): Restaurant? {
        val response = api.createRestaurant(body)
        return response.data?.toDomain()
    }

    override suspend fun getRestaurant(): Restaurant? {
        val response = api.getRestaurantInfo()
        return response.data?.toDomain()
    }
}

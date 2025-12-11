package com.example.casestudy.data.mapper

import com.example.casestudy.data.remote.dto.restaurant.RestaurantResponse
import com.example.casestudy.domain.model.Restaurant

fun RestaurantResponse.toDomain(): Restaurant {
    return Restaurant(
        id = this.id ?: -1,
        name = this.name.orEmpty(),
        email = this.email.orEmpty()
    )
}
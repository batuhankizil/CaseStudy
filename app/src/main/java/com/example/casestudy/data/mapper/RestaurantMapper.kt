package com.example.casestudy.data.mapper

import com.example.casestudy.data.remote.dto.restaurant.RestaurantResponse
import com.example.casestudy.domain.model.Restaurant

fun RestaurantResponse.toDomain(): Restaurant {
    return Restaurant(
        id = this.id ?: -1,
        name = this.name.orEmpty(),
        description = this.description.orEmpty(),
        physicalAddress = this.physical_address.orEmpty(),
        phone = this.phone.orEmpty(),
        email = this.email.orEmpty(),
        city = this.city.orEmpty(),
        country = this.country.orEmpty(),
        logo = this.logo.orEmpty()
    )
}
package com.example.casestudy.data.remote.dto.restaurant

data class RestaurantResponse(
    val id: Int?,
    val name: String?,
    val description: String?,
    val physical_address: String?,
    val phone: String?,
    val email: String?,
    val city: String?,
    val logo: String?,
    val country: String?,
    val main_language: String?,
    val support_menu_language_ids: String?,
    val operation_start_time: String?,
    val operation_end_time: String?,
    val city_id: String?,
    val district_id: String?,
    val neighborhood_id: String?
)
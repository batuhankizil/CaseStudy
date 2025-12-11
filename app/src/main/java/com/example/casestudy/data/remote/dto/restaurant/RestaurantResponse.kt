package com.example.casestudy.data.remote.dto.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("physical_address")
    val physical_address: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("main_language")
    val main_language: String?,
    @SerializedName("support_menu_lnaguage_ids")
    val support_menu_language_ids: String?,
    @SerializedName("operation_start_time")
    val operation_start_time: String?,
    @SerializedName("operation_end_time")
    val operation_end_time: String?,
    @SerializedName("city_id")
    val city_id: String?,
    @SerializedName("district_id")
    val district_id: String?,
    @SerializedName("neighborhood_id")
    val neighborhood_id: String?
)
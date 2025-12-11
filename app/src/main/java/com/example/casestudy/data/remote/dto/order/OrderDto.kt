package com.example.casestudy.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class OrderDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("total_price")
    val totalPrice: Double,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("items")
    val items: List<OrderItemDto>?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?
)

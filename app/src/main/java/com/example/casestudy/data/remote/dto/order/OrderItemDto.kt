package com.example.casestudy.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class OrderItemDto(
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("product_id")
    val productId: Int
)
package com.example.casestudy.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class OrderStatusRequest(
    @SerializedName("order_unique_code")
    val order_unique_code: String,
    @SerializedName("status")
    val status: String
)

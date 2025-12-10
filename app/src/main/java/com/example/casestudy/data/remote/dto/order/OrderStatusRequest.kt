package com.example.casestudy.data.remote.dto.order

data class OrderStatusRequest(
    val order_unique_code: String,
    val status: String
)

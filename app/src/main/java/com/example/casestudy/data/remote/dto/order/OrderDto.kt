package com.example.casestudy.data.remote.dto.order

data class OrderDto(
    val order_id: Int,
    val unique_code: String,
    val customer_name: String,
    val order_details: OrderDetailsDto
)

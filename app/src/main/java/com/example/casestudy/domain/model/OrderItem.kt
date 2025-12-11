package com.example.casestudy.domain.model

data class OrderItem(
    val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int
)

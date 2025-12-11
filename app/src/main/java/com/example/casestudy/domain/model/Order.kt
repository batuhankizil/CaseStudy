package com.example.casestudy.domain.model

data class Order(
    val id: Int,
    val status: String,
    val totalPrice: Double,
    val createdAt: String,
    val items: List<OrderItem>,
    val lat: Double?,
    val lng: Double?
)
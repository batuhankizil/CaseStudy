package com.example.casestudy.domain.model

data class Order(
    val id: Int,
    val uniqueCode: String,
    val customerName: String,
    val customerPhone: String,
    val customerEmail: String?,
    val deliveryAddress: Address,
    val orderDetails: OrderDetails,
    val items: List<OrderItem>,
    val createdAt: String
)
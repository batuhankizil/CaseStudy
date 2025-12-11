package com.example.casestudy.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class OrderDto(
    @SerializedName("order_id")
    val id: Int,
    @SerializedName("unique_code")
    val uniqueCode: String,
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("customer_phone")
    val customerPhone: String,
    @SerializedName("customer_email")
    val customerEmail: String?,
    @SerializedName("delivery_address")
    val deliveryAddress: AddressDto,
    @SerializedName("order_details")
    val orderDetails: OrderDetailsDto,
    @SerializedName("items")
    val items: List<OrderItemDto>,
    @SerializedName("created_at")
    val createdAt: String
)

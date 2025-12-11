package com.example.casestudy.data.mapper

import com.example.casestudy.data.remote.dto.order.OrderDto
import com.example.casestudy.data.remote.dto.order.OrderItemDto
import com.example.casestudy.domain.model.Order
import com.example.casestudy.domain.model.OrderItem

fun OrderItemDto.toDomain(): OrderItem {
    return OrderItem(
        productId = this.productId,
        productName = this.productName,
        price = this.price,
        quantity = this.quantity
    )
}

fun OrderDto.toDomain(): Order {
    return Order(
        id = this.id,
        status = this.status,
        totalPrice = this.totalPrice,
        createdAt = this.createdAt,
        items = this.items?.map { it.toDomain() }.orEmpty(),
        lat = this.lat,
        lng = this.lng
    )
}
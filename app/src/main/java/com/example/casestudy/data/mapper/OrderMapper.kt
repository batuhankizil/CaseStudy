package com.example.casestudy.data.mapper

import com.example.casestudy.data.remote.dto.order.AddressDto
import com.example.casestudy.data.remote.dto.order.OrderDetailsDto
import com.example.casestudy.data.remote.dto.order.OrderDto
import com.example.casestudy.data.remote.dto.order.OrderItemDto
import com.example.casestudy.domain.model.Address
import com.example.casestudy.domain.model.Order
import com.example.casestudy.domain.model.OrderDetails
import com.example.casestudy.domain.model.OrderItem

fun OrderItemDto.toDomain(): OrderItem {
    return OrderItem(
        productId = this.productId ?: 0,
        productName = this.productName,
        price = this.price,
        quantity = this.quantity,
        total = this.total
    )
}

fun OrderDto.toDomain(): Order {
    return Order(
        id = this.id,
        uniqueCode = this.uniqueCode,
        customerName = this.customerName,
        customerPhone = this.customerPhone,
        customerEmail = this.customerEmail,
        deliveryAddress = this.deliveryAddress.toDomain(),
        orderDetails = this.orderDetails.toDomain(),
        items = this.items.map { it.toDomain() },
        createdAt = this.createdAt
    )
}

fun AddressDto.toDomain(): Address {
    return Address(
        fullAddress = this.fullAddress,
        city = this.city,
        district = this.district,
        neighborhood = this.neighborhood
    )
}

fun OrderDetailsDto.toDomain(): OrderDetails {
    return OrderDetails(
        totalAmount = this.total_amount,
        finalAmount = this.final_amount,
        status = this.status
    )
}
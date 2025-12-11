package com.example.casestudy.ui.order

import com.example.casestudy.domain.model.Order

data class OrderUiState(
    val loading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val error: String? = null
)
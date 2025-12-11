package com.example.casestudy.ui.restaurant

import com.example.casestudy.domain.model.Restaurant

data class RestaurantUiState(
    val loading: Boolean = false,
    val restaurant: Restaurant? = null,
    val success: Boolean = false,
    val error: String? = null
)
package com.example.casestudy.domain.model

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val physicalAddress: String,
    val phone: String,
    val email: String,
    val city: String,
    val country: String,
    val logo: String
)
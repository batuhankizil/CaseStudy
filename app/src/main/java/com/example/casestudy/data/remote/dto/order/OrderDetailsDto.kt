package com.example.casestudy.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class OrderDetailsDto(
    @SerializedName("total_amount")
    val total_amount: Double,
    @SerializedName("final_amount")
    val final_amount: Double,
    @SerializedName("status")
    val status: String
)

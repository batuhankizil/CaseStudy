package com.example.casestudy.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("full_address")
    val fullAddress: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("neighborhood")
    val neighborhood: String
)

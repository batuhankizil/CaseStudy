package com.example.casestudy.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: UserDto?
)
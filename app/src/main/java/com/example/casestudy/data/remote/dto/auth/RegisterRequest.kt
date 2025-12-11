package com.example.casestudy.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("password_confirmation")
    val password_confirmation: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("bussiness_name")
    val bussiness_name: String,
    @SerializedName("bussiness_phone")
    val bussiness_phone: String,
    @SerializedName("bussiness_email")
    val bussiness_email: String
)

package com.example.casestudy.data.remote.dto.common

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T?
)

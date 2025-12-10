package com.example.casestudy.data.remote.dto.common

data class BaseResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: T?
)

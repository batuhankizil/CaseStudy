package com.example.casestudy.data.remote.dto.socket

import com.google.gson.annotations.SerializedName

data class SocketAuthResponse(
    @SerializedName("auth")
    val auth: String
)

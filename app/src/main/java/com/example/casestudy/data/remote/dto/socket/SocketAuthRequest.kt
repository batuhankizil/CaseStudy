package com.example.casestudy.data.remote.dto.socket

import com.google.gson.annotations.SerializedName

data class SocketAuthRequest(
    @SerializedName("socket_id")
    val socket_id: String,
    @SerializedName("channel_name")
    val channel_name: String
)

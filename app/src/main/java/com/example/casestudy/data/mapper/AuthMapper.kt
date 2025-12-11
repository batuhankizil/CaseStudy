package com.example.casestudy.data.mapper

import com.example.casestudy.data.remote.dto.auth.AuthResponse
import com.example.casestudy.domain.model.User

fun AuthResponse.toDomain(): User {
    return User(
        id = this.user.id,
        name = this.user.name,
        email = this.user.email
    )
}
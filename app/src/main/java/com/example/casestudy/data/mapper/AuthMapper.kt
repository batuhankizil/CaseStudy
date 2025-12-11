package com.example.casestudy.data.mapper

import com.example.casestudy.data.remote.dto.auth.UserDto
import com.example.casestudy.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email
    )
}
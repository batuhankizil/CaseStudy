package com.example.casestudy.domain.repository.auth

import com.example.casestudy.data.remote.dto.auth.LoginRequest
import com.example.casestudy.data.remote.dto.auth.RegisterRequest
import com.example.casestudy.domain.model.User

interface AuthRepository {

    suspend fun register(body: RegisterRequest): User?
    suspend fun login(body: LoginRequest): User?
}
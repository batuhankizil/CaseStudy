package com.example.casestudy.data.repository.auth

import com.example.casestudy.data.local.preferences.AuthPreferences
import com.example.casestudy.data.mapper.toDomain
import com.example.casestudy.data.remote.api.ApiService
import com.example.casestudy.data.remote.dto.auth.LoginRequest
import com.example.casestudy.data.remote.dto.auth.RegisterRequest
import com.example.casestudy.domain.model.User
import com.example.casestudy.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val prefs: AuthPreferences
) : AuthRepository {

    override suspend fun register(body: RegisterRequest): User? {
        val response = api.register(body)
        response.data?.token?.let { prefs.saveToken(it) }
        return response.data?.toDomain()
    }

    override suspend fun login(body: LoginRequest): User? {
        val response = api.login(body)
        response.data?.token?.let { prefs.saveToken(it) }
        return response.data?.toDomain()
    }
}
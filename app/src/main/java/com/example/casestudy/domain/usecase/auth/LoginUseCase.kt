package com.example.casestudy.domain.usecase.auth

import com.example.casestudy.data.remote.dto.auth.LoginRequest
import com.example.casestudy.domain.model.User
import com.example.casestudy.domain.repository.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(body: LoginRequest): Result<User?> {
        return try {
            val user = repo.login(body)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
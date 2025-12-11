package com.example.casestudy.domain.usecase.auth

import com.example.casestudy.data.remote.dto.auth.RegisterRequest
import com.example.casestudy.domain.model.User
import com.example.casestudy.domain.repository.auth.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(body: RegisterRequest): Result<User?> {
        return try {
            val result = repo.register(body)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
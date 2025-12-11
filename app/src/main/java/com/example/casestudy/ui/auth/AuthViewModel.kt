package com.example.casestudy.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.data.remote.dto.auth.LoginRequest
import com.example.casestudy.data.remote.dto.auth.RegisterRequest
import com.example.casestudy.domain.usecase.auth.LoginUseCase
import com.example.casestudy.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state = _state.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {

            _state.value = AuthUiState(loading = true)

            val result = loginUseCase(LoginRequest(email, password))

            result.onSuccess { user ->
                if (user != null) {
                    _state.value = AuthUiState(
                        loading = false,
                        success = true
                    )
                } else {
                    _state.value = AuthUiState(
                        loading = false,
                        error = "Invalid credentials"
                    )
                }
            }.onFailure { error ->
                _state.value = AuthUiState(
                    loading = false,
                    error = error.message ?: "Unexpected error"
                )
            }
        }
    }

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        businessName: String,
        businessPhone: String,
        businessEmail: String
    ) {
        viewModelScope.launch {

            _state.value = AuthUiState(loading = true)

            val result = registerUseCase(
                RegisterRequest(
                    name = name,
                    email = email,
                    password = password,
                    password_confirmation = confirmPassword,
                    phone = phone,
                    bussiness_name = businessName,
                    bussiness_phone = businessPhone,
                    bussiness_email = businessEmail
                )
            )

            result.onSuccess { user ->
                if (user != null) {
                    _state.value = AuthUiState(
                        loading = false,
                        success = true
                    )
                } else {
                    _state.value = AuthUiState(
                        loading = false,
                        error = "Register failed"
                    )
                }
            }.onFailure { error ->
                _state.value = AuthUiState(
                    loading = false,
                    error = error.message ?: "Unexpected error"
                )
            }
        }
    }
}
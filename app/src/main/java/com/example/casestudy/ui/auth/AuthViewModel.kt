package com.example.casestudy.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.data.remote.dto.auth.LoginRequest
import com.example.casestudy.data.remote.dto.auth.RegisterRequest
import com.example.casestudy.domain.usecase.auth.LoginUseCase
import com.example.casestudy.domain.usecase.auth.RegisterUseCase
import com.example.casestudy.domain.usecase.restaurant.GetRestaurantUseCase
import com.example.casestudy.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val getRestaurantUseCase: GetRestaurantUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state = _state.asStateFlow()

    private val _destination = MutableStateFlow<String?>(null)
    val destination = _destination.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthUiState(loading = true)
            val result = loginUseCase(LoginRequest(email, password))
            handleAuthResult(result)
        }
    }

    fun register(
        name: String, email: String, password: String, confirmPassword: String,
        phone: String, businessName: String, businessPhone: String, businessEmail: String
    ) {
        viewModelScope.launch {
            _state.value = AuthUiState(loading = true)
            val result = registerUseCase(
                RegisterRequest(
                    name,
                    email,
                    password,
                    confirmPassword,
                    phone,
                    businessName,
                    businessPhone,
                    businessEmail
                )
            )
            handleAuthResult(result)
        }
    }

    private suspend fun handleAuthResult(result: Result<com.example.casestudy.domain.model.User?>) {
        if (result.isSuccess && result.getOrNull() != null) {
            val restaurantResult = getRestaurantUseCase()
            if (restaurantResult.isSuccess && restaurantResult.getOrNull() != null) {
                _destination.value = Screen.Dashboard.route
            } else {
                _destination.value = Screen.CreateRestaurant.route
            }

            _state.value = AuthUiState(loading = false, success = true)
        } else {
            _state.value = AuthUiState(
                loading = false,
                error = result.exceptionOrNull()?.message ?: "Authentication failed"
            )
        }
    }

    fun onNavigationHandled() {
        _destination.value = null
    }
}
package com.example.casestudy.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.data.remote.dto.restaurant.RestaurantCreateRequest
import com.example.casestudy.domain.usecase.restaurant.CreateRestaurantUseCase
import com.example.casestudy.domain.usecase.restaurant.GetRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val getRestaurantUseCase: GetRestaurantUseCase,
    private val createRestaurantUseCase: CreateRestaurantUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RestaurantUiState())
    val state = _state.asStateFlow()

    fun loadRestaurant() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)

            val result = getRestaurantUseCase()

            result.onSuccess { restaurant ->
                if (restaurant == null || restaurant.id == 0) {
                     _state.value = RestaurantUiState(
                        loading = false,
                        error = "NO_RESTAURANT"
                    )
                } else {
                    _state.value = RestaurantUiState(
                        loading = false,
                        restaurant = restaurant,
                        success = true
                    )
                }
            }.onFailure { error ->
                _state.value = RestaurantUiState(
                    loading = false,
                    error = error.message
                )
            }
        }
    }

    fun createRestaurant(body: RestaurantCreateRequest) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)

            val result = createRestaurantUseCase(body)

            result.onSuccess { restaurant ->
                _state.value = RestaurantUiState(
                    loading = false,
                    restaurant = restaurant,
                    success = true
                )
            }.onFailure { error ->
                _state.value = RestaurantUiState(
                    loading = false,
                    error = error.message
                )
            }
        }
    }
}
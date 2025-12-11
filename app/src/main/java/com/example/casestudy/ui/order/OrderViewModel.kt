package com.example.casestudy.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casestudy.data.local.preferences.AuthPreferences
import com.example.casestudy.data.remote.socket.OrderSocketService
import com.example.casestudy.domain.usecase.order.CacheOrderUseCase
import com.example.casestudy.domain.usecase.order.GetOrdersUseCase
import com.example.casestudy.domain.usecase.order.UpdateOrderStatusUseCase
import com.example.casestudy.domain.usecase.restaurant.GetRestaurantUseCase
import kotlinx.coroutines.flow.firstOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase,
    private val cacheOrderUseCase: CacheOrderUseCase,
    private val socketService: OrderSocketService,
    private val authPreferences: AuthPreferences,
    private val getRestaurantUseCase: GetRestaurantUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OrderUiState())
    val state = _state.asStateFlow()

    init {
        loadOrders()
        connectSocket()
    }

    private fun connectSocket() {
        viewModelScope.launch {
            val token = authPreferences.tokenFlow.firstOrNull()
            val restaurantResult = getRestaurantUseCase()
            val restaurant = restaurantResult.getOrNull()

            if (token != null && restaurant != null && restaurant.id != 0) {
                socketService.connect(token, restaurant.id)
                observeSocket()
            }
        }
    }

    fun loadOrders() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)

            val result = getOrdersUseCase()

            result.onSuccess { orders ->
                _state.value = OrderUiState(
                    loading = false,
                    orders = orders
                )
            }.onFailure { error ->
                _state.value = OrderUiState(
                    loading = false,
                    error = error.message
                )
            }
        }
    }

    fun updateOrderStatus(uniqueCode: String, newStatus: String) {
        viewModelScope.launch {
            val result = updateOrderStatusUseCase(uniqueCode, newStatus)

            result.onSuccess { success ->
                if (success) {
                    val updatedOrders = _state.value.orders.map { order ->
                        if (order.uniqueCode == uniqueCode) {
                            order.copy(
                                orderDetails = order.orderDetails.copy(status = newStatus)
                            )
                        } else {
                            order
                        }
                    }
                    _state.value = _state.value.copy(orders = updatedOrders)
                }
            }.onFailure { error ->
                _state.value = _state.value.copy(
                    error = error.message
                )
            }
        }
    }

    private fun observeSocket() {
        viewModelScope.launch {
            socketService.orderFlow.collect { newOrder ->
                cacheOrderUseCase(newOrder)

                val updated = _state.value.orders.toMutableList()
                if (updated.none { it.uniqueCode == newOrder.uniqueCode }) {
                    updated.add(0, newOrder)
                    _state.value = _state.value.copy(orders = updated)
                }
            }
        }
    }
}
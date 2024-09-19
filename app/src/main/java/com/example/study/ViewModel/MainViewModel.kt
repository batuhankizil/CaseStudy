package com.example.study.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.study.domain.CategoryUIModel
import com.example.study.domain.usecase.ProductUseCase
import com.example.study.domain.FoodsUIModel
import com.example.study.domain.usecase.CategoryUseCase
import com.example.study.domain.usecase.UpdateCategoryUseCase
import com.example.study.sharedPreferences.LoginDataSource
import com.example.study.viewState.HomePageViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
    private val productUseCase: ProductUseCase,
    private val loginDataSource: LoginDataSource,
    private val updateCategoryUseCase: UpdateCategoryUseCase
) : ViewModel() {

    private val _categoryItems = MutableStateFlow<List<CategoryUIModel>>(emptyList())
    fun getCategoryModelStateFlow(): Flow<List<CategoryUIModel>> = _categoryItems

    private val _foodItems = MutableStateFlow<List<FoodsUIModel>>(emptyList())
    fun getFoodsModelStateFlow(): Flow<List<FoodsUIModel>> = _foodItems

    private val _usernameViewState = MutableStateFlow(HomePageViewState())
    val username: Flow<HomePageViewState> get() = _usernameViewState

    init {
        loadFoodItems()
        loadUsername()
        loadCategoryItems()
    }

    private fun loadCategoryItems() {
        viewModelScope.launch {
            _categoryItems.value = categoryUseCase.fetchCategory()
        }
    }

    private fun loadFoodItems() {
        viewModelScope.launch {
            _foodItems.value = productUseCase.fetchProducts()
        }
    }

    private fun loadUsername() {
        _usernameViewState.value =
            _usernameViewState.value.copy(username = loginDataSource.userName)
    }

    fun updateCategoryList(selectedItemId: Int) {
        _categoryItems.value =
            updateCategoryUseCase.updateCategorySelection(_categoryItems.value, selectedItemId)
    }

}
package com.example.study.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.study.R
import com.example.study.domain.usecase.ProductUseCase
import com.example.study.model.CategoryModel
import com.example.study.data.FoodsModelResponse
import com.example.study.domain.FoodsUIModel
import com.example.study.domain.ProductDecider
import com.example.study.domain.mapper.ProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val productMapper = ProductMapper(ProductDecider())


    private val _categoryModel = MutableLiveData<List<CategoryModel>>()
    fun getCategoryModelLiveData(): LiveData<List<CategoryModel>> = _categoryModel

    private val _foodItems = MutableStateFlow<List<FoodsUIModel>>(emptyList())
    fun getFoodsModelStateFlow(): Flow<List<FoodsUIModel>> = _foodItems

    init {
        fetchCategoryModel()
        loadFoodItems()
    }

    private fun loadFoodItems() {
        viewModelScope.launch {
            val foods = productUseCase.fetchProducts()
            val uiModels = productMapper.mapFromResponseList(foods)
            _foodItems.value = uiModels
        }
    }



    /*private fun getModels() {
        _foodItems.value = listOf(
            FoodsModelResponse(4.8, R.drawable.chicken_burger, "Chicken burger", "200 gr chicken + cheese Lettuce + tomato", 28.00, 1, true),
            FoodsModelResponse(4.5, R.drawable.chese_burger, "Cheese burger", "200 gr meat + Lettuce cheese + onion + tomato", 25.00, 2, false),
            FoodsModelResponse(4.3, R.drawable.chicken_burger, "Chicken burger", "200 gr chicken + cheese Lettuce + tomato", 25.00, 3, false),
            FoodsModelResponse(4.3, R.drawable.chicken_burger, "Chicken burger", "200 gr chicken + cheese Lettuce + tomato", 25.00, 4, false),
            FoodsModelResponse(4.3, R.drawable.chese_burger, "Cheese burger", "200 gr chicken + cheese Lettuce + tomato", 25.00, 5, false),
            FoodsModelResponse(4.3, R.drawable.chese_burger, "Cheese burger", "200 gr chicken + cheese Lettuce + tomato", 25.00, 6, false)
        )
    }*/

    private fun fetchCategoryModel() {
        _categoryModel.value = buildList {
            add(CategoryModel(1, "Hamburger", R.drawable.burger, true))
            add(CategoryModel(2, "Pizza", R.drawable.pizza, false))
            add(CategoryModel(3, "Sandwich", R.drawable.sandwich, false))
        }
    }

    fun updateCategoryList(selectedItemId: Int) {
        _categoryModel.value = _categoryModel.value?.map {
            if (it.id == selectedItemId) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
    }

}
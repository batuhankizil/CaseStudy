package com.example.study.ViewModel

import androidx.lifecycle.ViewModel
import com.example.study.domain.FoodsUIModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FoodDetailViewModel : ViewModel() {

    private val gson = Gson()
    private val _foodDetail = MutableStateFlow<FoodsUIModel?>(null)
    val foodDetail: StateFlow<FoodsUIModel?> get() = _foodDetail

    fun setFoodJson(foodJson: String?) {
        val foodsModel = gson.fromJson(foodJson, FoodsUIModel::class.java)
        _foodDetail.value = foodsModel
    }
}
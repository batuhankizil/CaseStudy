package com.example.study.retrofit

import com.example.study.model.CategoryModel
import com.example.study.model.FoodModel
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCategories(): List<CategoryModel> {
        return apiService.getCategories()
    }

    suspend fun getFoods(): List<FoodModel> {
        return apiService.getFoods()
    }
}
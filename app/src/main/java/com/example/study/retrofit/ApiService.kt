package com.example.study.retrofit

import com.example.study.model.CategoryModel
import com.example.study.model.FoodModel
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): List<CategoryModel>

    @GET("foods")
    suspend fun getFoods(): List<FoodModel>
}
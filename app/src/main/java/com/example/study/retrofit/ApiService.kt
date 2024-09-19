package com.example.study.retrofit

import com.example.study.model.CategoryModel
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): List<CategoryModel>
}
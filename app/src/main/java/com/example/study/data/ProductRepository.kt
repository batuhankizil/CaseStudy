package com.example.study.data

import android.content.Context
import com.example.study.domain.usecase.ProductUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val context: Context,

){

    private val gson = Gson()

    fun fetchFoods(): List<FoodsModelResponse> {
        val json = loadJsonFromAssets("foodsResponse.json")

        val listType = object : TypeToken<List<FoodsModelResponse>>() {}.type
        return gson.fromJson(json, listType)
    }

    private fun loadJsonFromAssets(filename: String): String {
        return context.assets.open(filename).bufferedReader().use { it.readText() }
    }

}
package com.example.study.domain.usecase

import com.example.study.domain.FoodsUIModel
import com.example.study.domain.mapper.FoodMapper
import com.example.study.retrofit.DataRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: DataRepository,
    private val foodMapper: FoodMapper
) {

    suspend fun fetchProducts(): List<FoodsUIModel> {
        val response = repository.getFoods()
        return response.map {
            foodMapper.mapToFoodUIModel(it)
        }
    }

}
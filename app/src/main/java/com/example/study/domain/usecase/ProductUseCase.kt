package com.example.study.domain.usecase

import com.example.study.data.FoodsModelResponse
import com.example.study.domain.FoodsUIModel
import com.example.study.domain.mapper.ProductMapper
import com.example.study.data.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val mapper: ProductMapper
) {

    fun fetchProducts() : List<FoodsModelResponse> {
        val response = repository.fetchFoods()
        return response
    }

}
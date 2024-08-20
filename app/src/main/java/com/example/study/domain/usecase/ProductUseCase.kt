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
    // Repository kullanarak FoodsModeli al. Sonra hesaplamaları yaparak FoodsUIModele dönüştür. FoodsModeli mapper kullanarak FoodsUIModele dönüştür.
    /*fun calculateDiscountedPrice(foodsModel: FoodsModelResponse): Double {
        val discountRate = 0.10
        val originalPrice = foodsModel.foodPrice ?: 0.0
        return if (foodsModel.discount == true) {
            originalPrice * (1 - discountRate)
        } else
            originalPrice
    }

    fun getFoodDisplayData(foodsModel: FoodsModelResponse): FoodDisplayData {
        val discountedPrice = calculateDiscountedPrice(foodsModel)
        return FoodDisplayData(
            salePrice = foodsModel.foodPrice!!,
            discountedPrice = discountedPrice,
            isDiscounted = true
        )
    }

    data class FoodDisplayData(
        val salePrice: Double,
        val discountedPrice: Double,
        val isDiscounted: Boolean
    )*/


}
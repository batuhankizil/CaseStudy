package com.example.study.domain.mapper

import com.example.study.domain.FoodsUIModel
import com.example.study.domain.ProductDecider
import com.example.study.model.FoodModel
import javax.inject.Inject

class FoodMapper @Inject constructor(
    private val productDecider: ProductDecider
) {

    companion object {
        private const val DEFAULT_IMAGE_URL =
            "https://glouton.b-cdn.net/site/images/no-image-wide.png"
    }

    fun mapToFoodUIModel(foodModel: FoodModel): FoodsUIModel {

        val oldPrice = foodModel.foodPrice
        val hasDiscount = foodModel.discount

        val discountPrice = productDecider.decideDiscountPrice(oldPrice, hasDiscount)

        return FoodsUIModel(
            id = foodModel.id,
            foodRank = foodModel.foodRank,
            foodImage = foodModel.foodImage ?: DEFAULT_IMAGE_URL,
            foodName = foodModel.foodName,
            foodDetail = foodModel.foodDetail,
            foodPrice = foodModel.foodPrice,
            discount = foodModel.discount,
            discountPrice = discountPrice
        )
    }
}
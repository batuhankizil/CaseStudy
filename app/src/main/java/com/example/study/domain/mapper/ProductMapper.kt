package com.example.study.domain.mapper

import com.example.study.R
import com.example.study.data.FoodsModelResponse
import com.example.study.domain.FoodsUIModel

class ProductMapper {

    fun mapFromResponseList(responseList: List<FoodsModelResponse>): List<FoodsUIModel> {
        return responseList.map { mapFromResponse(it) }
    }

    private fun mapFromResponse(foodsModel: FoodsModelResponse): FoodsUIModel {
        val oldPrice = foodsModel.foodPrice ?: 0.0
        val hasDiscount = foodsModel.discount ?: false
        val discountRate = 0.10
        val discountedPrice = if (hasDiscount) {
            oldPrice * (1 - discountRate)
        } else {
            oldPrice
        }

        return FoodsUIModel(
            foodRank = foodsModel.foodRank ?: 0.0,
            foodImage = foodsModel.foodImage ?: R.drawable.chicken_burger,
            foodName = foodsModel.foodName ?: "Unknown",
            foodDetail = foodsModel.foodDetail ?: "Unknown",
            foodPrice = foodsModel.foodPrice ?: 0.0,
            id = foodsModel.id ?: 0,
            discount = hasDiscount,
            discountPrice = discountedPrice
        )
    }



}

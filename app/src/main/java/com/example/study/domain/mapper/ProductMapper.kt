package com.example.study.domain.mapper

import com.example.study.R
import com.example.study.data.FoodsModelResponse
import com.example.study.domain.FoodsUIModel
import com.example.study.domain.ProductDecider
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val productDecider: ProductDecider
) {

    fun mapFromResponseList(responseList: List<FoodsModelResponse>): List<FoodsUIModel> {
        return responseList.map { mapFromResponse(it) }
    }

    fun mapFromResponse(foodsModel: FoodsModelResponse): FoodsUIModel {
        val oldPrice = foodsModel.foodPrice ?: 0.0
        val hasDiscount = foodsModel.discount ?: false

        productDecider.decideDiscountPrice(oldPrice, hasDiscount)

        return FoodsUIModel(
            foodRank = foodsModel.foodRank ?: 0.0,
            foodImage = foodsModel.foodImage?.takeIf { it.isNotEmpty() } ?: DEFAULT_IMAGE_URL,
            foodName = foodsModel.foodName ?: "Unknown",
            foodDetail = foodsModel.foodDetail ?: "Unknown",
            foodPrice = foodsModel.foodPrice ?: 0.0,
            id = foodsModel.id ?: 0,
            discount = hasDiscount,
            discountPrice = productDecider.decideDiscountPrice(oldPrice, hasDiscount)
        )
    }

    companion object {
        private const val DEFAULT_IMAGE_URL = "https://glouton.b-cdn.net/site/images/no-image-wide.png"
    }
}

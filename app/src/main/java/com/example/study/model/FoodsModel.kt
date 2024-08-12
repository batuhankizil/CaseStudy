package com.example.study.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodsModel (
    val foodRank: Double,
    val foodImage: Int,
    val foodName: String,
    val foodDetail: String,
    val foodPrice: Double,
    val id: Int,
    val discount: Boolean
) : Parcelable {

    fun getDiscountedPrice(): Double {
        val discountRate = 0.10
        return if (discount) foodPrice * (1 - discountRate) else foodPrice
    }

}
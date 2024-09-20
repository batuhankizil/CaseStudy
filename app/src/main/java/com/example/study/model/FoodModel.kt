package com.example.study.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class FoodModel (
    val id: Int,
    val foodRank: Double,
    val foodImage: String,
    val foodName: String,
    val foodDetail: String,
    val foodPrice: Double,
    val discount: Boolean,
    val discountPrice: Double
)
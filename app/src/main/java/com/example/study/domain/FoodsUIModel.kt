package com.example.study.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class FoodsUIModel (
    val foodRank: Double,
    val foodImage: Int,
    val foodName: String,
    val foodDetail: String,
    val foodPrice: Double,
    val id: Int,
    val discount: Boolean,
    val discountPrice: Double
)
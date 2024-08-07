package com.example.study.model

import android.os.Parcel
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
) : Parcelable
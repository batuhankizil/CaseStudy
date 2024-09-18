package com.example.study.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodsModelResponse (
    @SerializedName("foodRank") val foodRank: Double?,
    @SerializedName("foodImage") val foodImage: String?,
    @SerializedName("foodName") val foodName: String?,
    @SerializedName("foodDetail") val foodDetail: String?,
    @SerializedName("foodPrice") val foodPrice: Double?,
    @SerializedName("foodId") val id: Int?,
    @SerializedName("discount") val discount: Boolean?,
)

package com.example.study.model

import android.os.Parcel
import android.os.Parcelable


data class FoodsModel (
    val foodRank: Double,
    val foodImage: Int,
    val foodName: String,
    val foodDetail: String,
    val foodPrice: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(foodRank)
        parcel.writeInt(foodImage)
        parcel.writeString(foodName)
        parcel.writeString(foodDetail)
        parcel.writeDouble(foodPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FoodsModel> {
        override fun createFromParcel(parcel: Parcel): FoodsModel {
            return FoodsModel(parcel)
        }

        override fun newArray(size: Int): Array<FoodsModel?> {
            return arrayOfNulls(size)
        }
    }
}

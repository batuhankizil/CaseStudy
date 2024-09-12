package com.example.study.viewState

import android.graphics.Color
import android.graphics.Paint
import android.opengl.Visibility
import android.view.View
import com.example.study.domain.FoodsUIModel

data class FoodViewState(val foodsUIModel: FoodsUIModel) {

    fun getDiscountVisibility(): Int {
        return if (foodsUIModel.discount) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun getFoodPriceTextColor(): Int? {
        return if (foodsUIModel.discount) {
            Color.GRAY
        } else null
    }

    fun getFoodPriceStrikethrough(): Int {
        return if (foodsUIModel.discount) {
            Paint.STRIKE_THRU_TEXT_FLAG
        } else 0
    }

}
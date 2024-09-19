package com.example.study.viewState

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.study.R
import com.example.study.domain.CategoryUIModel
import com.example.study.model.CategoryModel

data class CategoryViewState(val categoryUIModel: CategoryUIModel) {

    fun getTextColor(): Int {
        return if (categoryUIModel.isSelected) {
            Color.White.toArgb()
        } else {
            Color.Black.toArgb()
        }
    }

    fun getBackgroundResource(): Int? {
        return if (categoryUIModel.isSelected) {
            R.drawable.button_add_to_cart
        } else {
            null
        }
    }

    fun updateSelection(selectedItemId: Int) {
        if (categoryUIModel.id == selectedItemId) {
            categoryUIModel.isSelected = true
        } else {
            categoryUIModel.isSelected = false
        }
    }

}
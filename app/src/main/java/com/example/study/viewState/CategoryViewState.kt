package com.example.study.viewState

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.study.R
import com.example.study.model.CategoryModel

data class CategoryViewState(val categoryModel: CategoryModel) {

    fun getTextColor(): Int {
        return if (categoryModel.isSelected) {
            Color.White.toArgb()
        } else {
            Color.Black.toArgb()
        }
    }

    fun getBackgroundResource(): Int? {
        return if (categoryModel.isSelected) {
            R.drawable.button_add_to_cart
        } else {
            null
        }
    }

    fun updateSelection(selectedItemId: Int) {
        if (categoryModel.id == selectedItemId) {
            categoryModel.isSelected = true
        } else {
            categoryModel.isSelected = false
        }
    }

}
package com.example.study.domain.usecase

import com.example.study.model.CategoryModel
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor() {
    fun updateCategorySelection(
        categoryList: List<CategoryModel>,
        selectedItemId: Int
    ): List<CategoryModel> {
        return categoryList.map {
            if (it.id == selectedItemId) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
    }
}
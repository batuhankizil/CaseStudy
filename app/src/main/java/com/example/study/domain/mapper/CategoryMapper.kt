package com.example.study.domain.mapper

import com.example.study.domain.CategoryUIModel
import com.example.study.model.CategoryModel
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun mapToUIModel(categoryModel: CategoryModel): CategoryUIModel {
        return CategoryUIModel(
            id = categoryModel.id,
            categoryName = categoryModel.categoryName,
            categoryIcon = categoryModel.categoryIcon ?: "Unknown",
            isSelected = categoryModel.isSelected
        )
    }

}
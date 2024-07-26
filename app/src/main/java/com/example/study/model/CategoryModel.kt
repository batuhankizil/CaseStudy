package com.example.study.model

data class CategoryModel (
    val id: Int,
    val categoryName: String,
    val categoryIcon: Int,
    var isSelected: Boolean
)
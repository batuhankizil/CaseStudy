package com.example.study.domain

data class CategoryUIModel (
    val id: Int,
    val categoryName: String,
    val categoryIcon: String,
    var isSelected: Boolean
)
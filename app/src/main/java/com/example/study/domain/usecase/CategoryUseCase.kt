package com.example.study.domain.usecase

import com.example.study.domain.CategoryUIModel
import com.example.study.domain.mapper.CategoryMapper
import com.example.study.retrofit.DataRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository: DataRepository,
    private val categoryMapper: CategoryMapper
) {
    suspend  fun fetchCategory(): List<CategoryUIModel> {
        val response = repository.getCategories()
        return response.map {
            categoryMapper.mapToUIModel(it)
        }
    }
}
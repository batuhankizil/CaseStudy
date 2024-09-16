package com.example.study.domain.usecase

import com.example.study.adapter.CollectiveModel
import com.example.study.domain.PostUIModel
import com.example.study.domain.mapper.PostMapper
import com.example.study.domain.mapper.PostToCollectiveModelMapper
import com.example.study.retrofit.DataRepository
import com.example.study.retrofit.Post
import javax.inject.Inject

class PostUseCase @Inject constructor(
    private val repository: DataRepository,
    private val postMapper: PostMapper,
    private val postToCollectiveModelMapper: PostToCollectiveModelMapper
) {
    suspend  fun fetchPost(): List<CollectiveModel> {
        val response = repository.getPosts()
        return response.map { postToCollectiveModelMapper.map(postMapper.mapToUIModel(it)) }
    }
}
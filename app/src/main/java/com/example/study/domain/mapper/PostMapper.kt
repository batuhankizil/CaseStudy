package com.example.study.domain.mapper

import com.example.study.domain.PostUIModel
import com.example.study.retrofit.Post
import javax.inject.Inject

class PostMapper @Inject constructor() {

    fun mapToUIModel(post: Post): PostUIModel {
        return PostUIModel(
            id = post.id,
            title = post.title
        )
    }

}
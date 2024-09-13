package com.example.study.domain.mapper

import com.example.study.adapter.CollectiveModel
import com.example.study.domain.PostUIModel
import com.example.study.retrofit.Comment
import com.example.study.retrofit.Post
import javax.inject.Inject

class PostToCollectiveModelMapper @Inject constructor() {

    fun map(postUIModel: PostUIModel): CollectiveModel {
        return CollectiveModel.Posts(
            post = Post(postUIModel.id, postUIModel.title, ""),
            comment = Comment(postUIModel.id, "No comments", postUIModel.id)
        )
    }
}

package com.example.study.retrofit

import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }

    suspend fun getComments(): List<Comment> {
        return apiService.getComments()
    }

}
package com.example.casestudy.data.di

import com.example.casestudy.data.remote.api.ApiService
import com.example.casestudy.data.remote.socket.OrderSocketService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @Singleton
    @Provides
    fun provideOrderSocketService(
        gson: Gson,
        client: OkHttpClient,
        api: ApiService
    ): OrderSocketService {
        return OrderSocketService(gson, client, api)
    }
}
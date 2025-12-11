package com.example.casestudy.data.di

import com.example.casestudy.data.repository.auth.AuthRepositoryImpl
import com.example.casestudy.data.repository.order.OrderRepositoryImpl
import com.example.casestudy.data.repository.restaurant.RestaurantRepositoryImpl
import com.example.casestudy.domain.repository.auth.AuthRepository
import com.example.casestudy.domain.repository.order.OrderRepository
import com.example.casestudy.domain.repository.restaurant.RestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        impl: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    @Singleton
    abstract fun bindRestaurantRepository(
        impl: RestaurantRepositoryImpl
    ): RestaurantRepository


}
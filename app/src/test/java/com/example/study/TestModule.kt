package com.example.study

import com.example.study.domain.ProductDecider
import com.example.study.domain.mapper.ProductMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {


    @Module
    @InstallIn(SingletonComponent::class)
    object TestModule {

        @Provides
        @Singleton
        fun provideProductMapper(productDecider: ProductDecider): ProductMapper {
            return ProductMapper(productDecider)
        }

        @Provides
        @Singleton
        fun provideProductDecider(): ProductDecider {
            return ProductDecider()
        }
    }

}

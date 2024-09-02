package com.example.study

import android.content.Context
import android.content.SharedPreferences
import com.example.study.data.ProductRepository
import com.example.study.domain.ProductDecider
import com.example.study.domain.mapper.ProductMapper
import com.example.study.domain.usecase.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("LoginPref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        @ApplicationContext context: Context
    ): ProductRepository {
        return ProductRepository(context)
    }

    @Provides
    @Singleton
    fun provideProductUseCase(
        repository: ProductRepository,
        mapper: ProductMapper
    ): ProductUseCase {
        return ProductUseCase(repository, mapper)
    }

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

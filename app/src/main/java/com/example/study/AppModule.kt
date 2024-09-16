package com.example.study

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.Data
import com.example.study.data.ProductRepository
import com.example.study.domain.ProductDecider
import com.example.study.domain.mapper.PostMapper
import com.example.study.domain.mapper.PostToCollectiveModelMapper
import com.example.study.domain.mapper.ProductMapper
import com.example.study.domain.usecase.ProductUseCase
import com.example.study.retrofit.ApiService
import com.example.study.retrofit.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Provides
    fun providePostMapper(): PostMapper {
        return PostMapper()
    }*/

    /*@Provides
    fun providePostToCollectiveModelMapper(): PostToCollectiveModelMapper {
        return PostToCollectiveModelMapper()
    }*/

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /*@Provides
    @Singleton
    fun provideDataRepository(
        apiService: ApiService
    ): DataRepository {
        return DataRepository(apiService)
    }*/

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

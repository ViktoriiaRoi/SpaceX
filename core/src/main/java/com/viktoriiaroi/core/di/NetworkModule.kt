package com.viktoriiaroi.core.di

import com.viktoriiaroi.core.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.spacexdata.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCompanyService(retrofit: Retrofit): CompanyService {
        return retrofit.create(CompanyService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideLaunchService(retrofit: Retrofit): LaunchService {
        return retrofit.create(LaunchService::class.java)
    }

    @Provides
    @Singleton
    fun provideRocketService(retrofit: Retrofit): RocketService {
        return retrofit.create(RocketService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoreService(retrofit: Retrofit): CoreService {
        return retrofit.create(CoreService::class.java)
    }
}
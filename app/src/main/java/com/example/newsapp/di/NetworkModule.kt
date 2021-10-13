package com.example.newsapp.di

import com.example.newsapp.data.network.RetrofitClient
import com.example.newsapp.data.network.apiservices.EverythingApiService
import com.example.newsapp.data.network.apiservices.SourceApiService
import com.example.newsapp.data.network.apiservices.TopHeadlinesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofit: RetrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun fetchEverything(): EverythingApiService {
        return retrofit.provideEverythingApiService()
    }

    @Singleton
    @Provides
    fun fetchTopHeadlines(): TopHeadlinesApiService {
        return retrofit.provideTopHeadlinesApiService()
    }

    @Singleton
    @Provides
    fun fetchSources(): SourceApiService {
        return retrofit.provideSourcesApiService()
    }
}
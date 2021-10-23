package com.example.newsapp.di

import com.example.newsapp.data.network.apiservices.EverythingApiService
import com.example.newsapp.data.network.apiservices.SourceApiService
import com.example.newsapp.data.network.apiservices.TopHeadlinesApiService
import com.example.newsapp.data.repositories.EverythingRepositoryImpl
import com.example.newsapp.data.repositories.SourcesRepositoryImpl
import com.example.newsapp.data.repositories.TopHeadlinesRepositoryImpl
import com.example.newsapp.domain.repositories.EverythingRepository
import com.example.newsapp.domain.repositories.SourcesRepository
import com.example.newsapp.domain.repositories.TopHeadlinesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideEverythingRepository(service: EverythingApiService): EverythingRepository =
        EverythingRepositoryImpl(service)

    @Provides
    fun provideTopHeadlinesRepository(service: TopHeadlinesApiService): TopHeadlinesRepository =
        TopHeadlinesRepositoryImpl(service)

    @Provides
    fun provideSourcesRepository(service: SourceApiService): SourcesRepository =
        SourcesRepositoryImpl(service)
}
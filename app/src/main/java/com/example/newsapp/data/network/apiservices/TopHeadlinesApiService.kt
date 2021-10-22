package com.example.newsapp.data.network.apiservices

import com.example.newsapp.data.dtos.models.EverythingModelDto
import com.example.newsapp.data.dtos.models.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesApiService {

    @GET("/v2/top-headlines?apiKey=ebe66846f18e453885e497d3f5015df5")
    suspend fun fetchTopHeadlines(
        @Query("country") query: String,
        @Query("page") page: Int
    ): NewsResponseDto<EverythingModelDto>
}
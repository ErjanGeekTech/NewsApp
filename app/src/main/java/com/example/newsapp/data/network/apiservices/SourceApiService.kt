package com.example.newsapp.data.network.apiservices

import com.example.newsapp.data.dtos.models.NewsResponseDto
import com.example.newsapp.data.dtos.models.SourcesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceApiService {

    @GET("/v2/top-headlines/sources?apiKey=ebe66846f18e453885e497d3f5015df5")
    suspend fun fetchSourcesCountryUs(
        @Query("country") query: String,
        @Query("page") page: Int
    ): NewsResponseDto<SourcesDto>
}
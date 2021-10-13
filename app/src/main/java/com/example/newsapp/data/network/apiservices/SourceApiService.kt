package com.example.newsapp.data.network.apiservices

import com.example.newsapp.models.EverythingModel
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.models.Sources
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceApiService {

    @GET("/v2/top-headlines/sources?apiKey=cee5ca142bd84c8b8975076757814681")
    suspend fun fetchSourcesCountryUs(
        @Query("country") query: String,
        @Query("page") page: Int
    ): NewsResponse<Sources>
}
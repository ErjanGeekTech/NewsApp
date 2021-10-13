package com.example.newsapp.data.network.apiservices

import com.example.newsapp.models.EverythingModel
import com.example.newsapp.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesApiService {

    @GET("/v2/top-headlines?apiKey=cee5ca142bd84c8b8975076757814681")
    suspend fun fetchTopHeadlines(
        @Query("country") query: String,
        @Query("page") page: Int
    ): NewsResponse<EverythingModel>
}
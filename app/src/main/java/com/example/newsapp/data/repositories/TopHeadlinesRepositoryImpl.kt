package com.example.newsapp.data.repositories

import com.example.newsapp.base.BaseRepository
import com.example.newsapp.data.dtos.toArticles
import com.example.newsapp.data.dtos.toNewsResponse
import com.example.newsapp.data.network.apiservices.TopHeadlinesApiService
import com.example.newsapp.domain.repositories.TopHeadlinesRepository
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(
    private val service: TopHeadlinesApiService
) : BaseRepository(), TopHeadlinesRepository {

    override fun fetchTopHeadlines(page: Int) = doRequest {
        service.fetchTopHeadlines("us", page).toNewsResponse().articles?.map { it.toArticles() }
    }
}
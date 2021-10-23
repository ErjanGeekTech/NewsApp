package com.example.newsapp.data.repositories

import com.example.newsapp.base.BaseRepository
import com.example.newsapp.data.dtos.toNewsResponse
import com.example.newsapp.data.dtos.toSources
import com.example.newsapp.data.network.apiservices.SourceApiService
import com.example.newsapp.domain.repositories.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val service: SourceApiService) :
    BaseRepository(), SourcesRepository {

    override fun fetchSources(page: Int) = doRequest {
        service.fetchSourcesCountryUs("us", 1).toNewsResponse().sources?.map { it.toSources() }
    }
}
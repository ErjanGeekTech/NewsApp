package com.example.newsapp.data.repositories

import com.example.newsapp.base.BaseRepository
import com.example.newsapp.data.dtos.toArticles
import com.example.newsapp.data.dtos.toNewsResponse
import com.example.newsapp.data.network.apiservices.EverythingApiService
import com.example.newsapp.domain.repositories.EverythingRepository
import javax.inject.Inject

class EverythingRepositoryImpl @Inject constructor(
    private val service: EverythingApiService
) : BaseRepository(), EverythingRepository {

    override fun fetchEverything(page: Int) = doRequest {
        service.fetchEverything("bitcoin", page).toNewsResponse().articles?.map { it.toArticles() }
    }
}
package com.example.newsapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.network.apiservices.SourceApiService
import com.example.newsapp.data.repositories.pagingSource.SourcesCountryUs
import com.example.newsapp.models.Sources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SourcesRepository @Inject constructor(private val service: SourceApiService) {

    fun fetchSourceCountryUs(): Flow<PagingData<Sources>> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false
        ), pagingSourceFactory = {
            SourcesCountryUs(service)
        }).flow
    }
}
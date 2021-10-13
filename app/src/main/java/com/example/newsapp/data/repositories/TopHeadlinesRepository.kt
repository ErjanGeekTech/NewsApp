package com.example.newsapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.base.BaseRepository
import com.example.newsapp.data.network.apiservices.TopHeadlinesApiService
import com.example.newsapp.data.repositories.pagingSource.TopHeadlinesPagingSource
import com.example.newsapp.models.EverythingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinesRepository @Inject constructor(
    private val service: TopHeadlinesApiService
) : BaseRepository() {

    fun fetchTopHeadlines(): Flow<PagingData<EverythingModel>> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false
        ), pagingSourceFactory = {
            TopHeadlinesPagingSource(service)
        }).flow
    }
}
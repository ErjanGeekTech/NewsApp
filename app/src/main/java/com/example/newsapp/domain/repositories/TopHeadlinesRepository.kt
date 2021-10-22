package com.example.newsapp.domain.repositories

import com.example.newsapp.common.Resource
import com.example.newsapp.domain.models.EverythingModel
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesRepository {

    fun fetchTopHeadlines(page: Int): Flow<Resource<List<EverythingModel>?>>
}
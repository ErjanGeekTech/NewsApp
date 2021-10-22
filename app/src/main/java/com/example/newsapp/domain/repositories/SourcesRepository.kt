package com.example.newsapp.domain.repositories

import com.example.newsapp.common.Resource
import com.example.newsapp.domain.models.Sources
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {

    fun fetchSources(page: Int): Flow<Resource<List<Sources>?>>
}
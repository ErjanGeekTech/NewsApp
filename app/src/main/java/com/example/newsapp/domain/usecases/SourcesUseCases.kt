package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.repositories.SourcesRepository
import javax.inject.Inject

class SourcesUseCases @Inject constructor(
    private val repository: SourcesRepository
) {

    operator fun invoke(page: Int) = repository.fetchSources(page)
}
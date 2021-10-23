package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.repositories.TopHeadlinesRepository
import javax.inject.Inject

class TopHeadlinesUseCases @Inject constructor(
    private val repository: TopHeadlinesRepository
) {

    operator fun invoke(page: Int) = repository.fetchTopHeadlines(page)
}
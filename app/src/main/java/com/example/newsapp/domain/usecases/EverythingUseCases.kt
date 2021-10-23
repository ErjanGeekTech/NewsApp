package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.repositories.EverythingRepository
import javax.inject.Inject

class EverythingUseCases @Inject constructor(
    private val repository: EverythingRepository
) {

    operator fun invoke(page: Int) = repository.fetchEverything(page)
}
package com.example.newsapp.domain.models

data class NewsResponse<T>(
    val status: String?,
    val totalResults: String?,
    val articles: List<T>?,
    val sources: List<T>?
)
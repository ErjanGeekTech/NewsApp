package com.example.newsapp.data.dtos

import com.example.newsapp.data.dtos.models.EverythingModelDto
import com.example.newsapp.data.dtos.models.NewsResponseDto
import com.example.newsapp.data.dtos.models.SourceDto
import com.example.newsapp.data.dtos.models.SourcesDto
import com.example.newsapp.domain.models.EverythingModel
import com.example.newsapp.domain.models.NewsResponse
import com.example.newsapp.domain.models.Source
import com.example.newsapp.domain.models.Sources

fun <T> NewsResponseDto<T>.toNewsResponse() = NewsResponse<T>(
    status, totalResults, articles, sources
)

fun EverythingModelDto.toArticles() =
    EverythingModel(
        source?.toSource(),
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt,
        content
    )

fun SourceDto.toSource() = Source(id, name)

fun SourcesDto.toSources() = Sources(id, name, description, url, category, language, country)
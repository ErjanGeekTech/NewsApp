package com.example.newsapp.data.dtos.models

import com.google.gson.annotations.SerializedName

data class NewsResponseDto<T>(

    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: String?,

    @SerializedName("articles")
    val articles: List<T>?,

    @SerializedName("sources")
    val sources: List<T>?
)
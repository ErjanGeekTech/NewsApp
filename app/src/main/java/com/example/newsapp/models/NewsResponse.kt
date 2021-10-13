package com.example.newsapp.models

import com.google.gson.annotations.SerializedName

data class NewsResponse<T>(

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: String,

    @SerializedName("articles")
    val articles: List<T>,

    @SerializedName("sources")
    val sources: List<T>
)
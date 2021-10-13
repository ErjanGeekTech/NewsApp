package com.example.newsapp.models

import com.example.newsapp.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName


data class EverythingModel(

    @SerializedName("source")
    val source: Source,

    @SerializedName("author")
    val author: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    override val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("content")
    val content: String,
) : IBaseDiffModel

data class Source(

    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String
)

data class Sources(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    override val url: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("language")
    val language: String,

    @SerializedName("country")
    val country: String
) : IBaseDiffModel
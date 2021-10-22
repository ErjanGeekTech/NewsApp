package com.example.newsapp.base

interface BaseFetchRequest {
    var page: Int
    fun fetchNews(page: Int)
}
package com.example.newsapp.data.network.interseptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("apiKey", "&apiKey=cee5ca142bd84c8b8975076757814681")
            .build()
        return chain.proceed(request)
    }
}
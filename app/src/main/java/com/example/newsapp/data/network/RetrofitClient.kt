package com.example.newsapp.data.network

import com.example.newsapp.constants.Constants.BASE_URL
import com.example.newsapp.data.network.apiservices.EverythingApiService
import com.example.newsapp.data.network.apiservices.SourceApiService
import com.example.newsapp.data.network.apiservices.TopHeadlinesApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun provideEverythingApiService(): EverythingApiService {
        return retrofit.create(EverythingApiService::class.java)
    }

    fun provideTopHeadlinesApiService(): TopHeadlinesApiService {
        return retrofit.create(TopHeadlinesApiService::class.java)
    }
    fun provideSourcesApiService(): SourceApiService {
        return retrofit.create(SourceApiService::class.java)
    }
}
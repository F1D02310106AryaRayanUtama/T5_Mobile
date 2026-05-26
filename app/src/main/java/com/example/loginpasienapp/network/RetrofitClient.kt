package com.example.loginpasienapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val URL_BASE = "https://api.pahrul.my.id/api/"

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val clientProvider = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val apiInstance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(clientProvider)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

package com.example.quotes.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    
    private  const val  BASE_URL = "https://zenquotes.io/api/"
    val api: QuotesApi by lazy {
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
            .create(QuotesApi::class.java)
    }

}
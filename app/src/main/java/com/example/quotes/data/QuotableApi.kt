package com.example.quotes.data

import retrofit2.http.GET
import kotlin.collections.List


interface QuotesApi {
    @GET("today")
//   suspend  fun getRandomQuote(): Quote
    suspend fun getTodayQuote():List<Quote>

    @GET("quotes ")
    suspend fun getListOfQuote(): List<Quote>

    @GET("random")
    suspend fun getRandomQuote():List<Quote>
}
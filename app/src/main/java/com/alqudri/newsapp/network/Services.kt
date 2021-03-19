package com.alqudri.newsapp.network

import com.alqudri.newsapp.model.headlines.Headlines
import com.alqudri.newsapp.model.newssource.NewsSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("sources")
    fun getNewsSource(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsSource>

    @GET("sources")
    fun getNewsSource(@Query("apiKey") apiKey: String): Call<NewsSource>

    @GET("top-headlines")
    fun getHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String
    ): Call<Headlines>

    @GET("everything")
    fun getHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
        @Query("q") q: String
    ): Call<Headlines>

    @GET("everything")
    fun getHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Call<Headlines>
}
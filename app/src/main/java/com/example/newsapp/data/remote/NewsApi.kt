package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.data.NewsModelResource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("source") source: String,
        @Query("apiKey") token: String
    ): NewsModelResource

}
package com.viktoriiaroi.core.network

import com.viktoriiaroi.core.network.model.news.NewsDTO
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("v4/history")
    suspend fun getNews(): Response<List<NewsDTO>>
}
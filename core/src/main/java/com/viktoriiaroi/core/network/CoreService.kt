package com.viktoriiaroi.core.network

import com.viktoriiaroi.core.network.model.core.CoreDTO
import com.viktoriiaroi.core.network.model.rocket.RocketDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoreService {
    @GET("v4/cores/{id}")
    suspend fun getCore(@Path("id") id: String): Response<CoreDTO>
}
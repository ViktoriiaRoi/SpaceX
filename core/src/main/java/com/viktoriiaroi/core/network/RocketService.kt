package com.viktoriiaroi.core.network

import com.viktoriiaroi.core.network.model.rocket.RocketDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketService {
    @GET("v4/rockets/{id}")
    suspend fun getRocket(@Path("id") id: String): Response<RocketDTO>
}
package com.viktoriiaroi.core.network

import com.viktoriiaroi.core.network.model.launch.LaunchDTO
import retrofit2.Response
import retrofit2.http.GET

interface LaunchService {
    @GET("v5/launches")
    suspend fun getAllLaunches(): Response<List<LaunchDTO>>

    @GET("v5/launches/past")
    suspend fun getPastLaunches(): Response<List<LaunchDTO>>

    @GET("v5/launches/upcoming")
    suspend fun getFutureLaunches(): Response<List<LaunchDTO>>
}
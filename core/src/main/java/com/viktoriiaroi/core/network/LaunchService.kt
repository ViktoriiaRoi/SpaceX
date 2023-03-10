package com.viktoriiaroi.core.network

import com.viktoriiaroi.core.network.model.launch.query.LaunchPage
import com.viktoriiaroi.core.network.model.launch.query.LaunchBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LaunchService {
    @POST("v5/launches/query")
    suspend fun getLaunches(@Body query: LaunchBody): Response<LaunchPage>
}
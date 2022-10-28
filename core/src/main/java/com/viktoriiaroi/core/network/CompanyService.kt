package com.viktoriiaroi.core.network

import com.viktoriiaroi.core.network.model.company.CompanyDTO
import retrofit2.Response
import retrofit2.http.GET

interface CompanyService {
    @GET("v4/company")
    suspend fun getCompany(): Response<CompanyDTO>
}
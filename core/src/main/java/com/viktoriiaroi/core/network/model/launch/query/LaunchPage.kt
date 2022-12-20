package com.viktoriiaroi.core.network.model.launch.query

import com.google.gson.annotations.SerializedName
import com.viktoriiaroi.core.network.model.launch.LaunchDTO

data class LaunchPage(
    @SerializedName("docs") var docs: List<LaunchDTO> = emptyList(),
    @SerializedName("totalDocs") var totalDocs: Int? = null,
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("limit") var limit: Int? = null,
    @SerializedName("totalPages") var totalPages: Int? = null,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("pagingCounter") var pagingCounter: Int? = null,
    @SerializedName("hasPrevPage") var hasPrevPage: Boolean? = null,
    @SerializedName("hasNextPage") var hasNextPage: Boolean? = null,
    @SerializedName("prevPage") var prevPage: String? = null,
    @SerializedName("nextPage") var nextPage: Int? = null,
)
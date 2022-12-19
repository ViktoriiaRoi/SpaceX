package com.viktoriiaroi.core.network.model.launch.pagination

import com.google.gson.annotations.SerializedName

data class LaunchOptions(
    @SerializedName("page") var page: Int,
    @SerializedName("sort") var sort: LaunchSort,
)
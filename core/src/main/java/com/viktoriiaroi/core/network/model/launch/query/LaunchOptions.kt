package com.viktoriiaroi.core.network.model.launch.query

import com.google.gson.annotations.SerializedName

data class LaunchOptions(
    @SerializedName("pagination") var pagination: Boolean,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("sort") var sort: LaunchSort? = null,
)
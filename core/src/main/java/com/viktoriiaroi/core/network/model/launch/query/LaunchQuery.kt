package com.viktoriiaroi.core.network.model.launch.query

import com.google.gson.annotations.SerializedName

data class LaunchQuery(
    @SerializedName("upcoming") var upcoming: Boolean? = null,
    @SerializedName("name") var name: SearchQuery? = null,
)
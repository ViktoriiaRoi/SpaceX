package com.viktoriiaroi.core.network.model.launch.pagination

import com.google.gson.annotations.SerializedName

data class LaunchQuery(
    @SerializedName("upcoming") var upcoming: Boolean? = null,
)
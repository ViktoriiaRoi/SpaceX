package com.viktoriiaroi.core.network.model.launch

import com.google.gson.annotations.SerializedName

data class FailuresDTO(
    @SerializedName("time") var time: Int? = null,
    @SerializedName("altitude") var altitude: String? = null,
    @SerializedName("reason") var reason: String? = null,
)
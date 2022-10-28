package com.viktoriiaroi.core.network.model.company

import com.google.gson.annotations.SerializedName

data class HeadquartersDTO(
    @SerializedName("address") var address: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("state") var state: String? = null,
)
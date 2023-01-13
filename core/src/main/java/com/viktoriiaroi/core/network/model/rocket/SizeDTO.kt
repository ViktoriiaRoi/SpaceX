package com.viktoriiaroi.core.network.model.rocket

import com.google.gson.annotations.SerializedName

data class SizeDTO(
    @SerializedName("meters") var meters: Double? = null,
    @SerializedName("feet") var feet: Double? = null,
)
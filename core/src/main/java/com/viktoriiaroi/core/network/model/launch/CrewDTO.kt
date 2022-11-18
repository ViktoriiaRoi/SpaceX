package com.viktoriiaroi.core.network.model.launch

import com.google.gson.annotations.SerializedName

data class CrewDTO(
    @SerializedName("crew") var crew: String? = null,
    @SerializedName("role") var role: String? = null,
)
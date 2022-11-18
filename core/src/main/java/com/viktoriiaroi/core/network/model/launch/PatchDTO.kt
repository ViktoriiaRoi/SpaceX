package com.viktoriiaroi.core.network.model.launch

import com.google.gson.annotations.SerializedName

data class PatchDTO(
    @SerializedName("small") var small: String? = null,
    @SerializedName("large") var large: String? = null,
)
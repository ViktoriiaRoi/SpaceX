package com.viktoriiaroi.core.network.model.rocket

import com.google.gson.annotations.SerializedName

data class MassDTO(
    @SerializedName("kg") var kg: Int? = null,
    @SerializedName("lb") var lb: Int? = null,
)
package com.viktoriiaroi.core.network.model.launch

import com.google.gson.annotations.SerializedName

data class FairingsDTO(
    @SerializedName("reused") var reused: Boolean? = null,
    @SerializedName("recovery_attempt") var recoveryAttempt: Boolean? = null,
    @SerializedName("recovered") var recovered: Boolean? = null,
    @SerializedName("ships") var ships: List<String> = listOf(),
)
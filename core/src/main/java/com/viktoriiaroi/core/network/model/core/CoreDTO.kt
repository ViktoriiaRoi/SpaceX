package com.viktoriiaroi.core.network.model.core

import com.google.gson.annotations.SerializedName

class CoreDTO(
    @SerializedName("block") var block: Int? = null,
    @SerializedName("reuse_count") var reuseCount: Int? = null,
    @SerializedName("rtls_attempts") var rtlsAttempts: Int? = null,
    @SerializedName("rtls_landings") var rtlsLandings: Int? = null,
    @SerializedName("asds_attempts") var asdsAttempts: Int? = null,
    @SerializedName("asds_landings") var asdsLandings: Int? = null,
    @SerializedName("last_update") var lastUpdate: String? = null,
    @SerializedName("launches") var launches: List<String> = listOf(),
    @SerializedName("serial") var serial: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("id") var id: String? = null,
)
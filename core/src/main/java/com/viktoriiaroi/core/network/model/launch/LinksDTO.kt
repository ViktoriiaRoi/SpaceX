package com.viktoriiaroi.core.network.model.launch

import com.google.gson.annotations.SerializedName


data class LinksDTO (
    @SerializedName("patch"      ) var patch     : PatchDTO?  = PatchDTO(),
    @SerializedName("presskit"   ) var presskit  : String? = null,
    @SerializedName("webcast"    ) var webcast   : String? = null,
    @SerializedName("youtube_id" ) var youtubeId : String? = null,
    @SerializedName("article"    ) var article   : String? = null,
    @SerializedName("wikipedia"  ) var wikipedia : String? = null
)
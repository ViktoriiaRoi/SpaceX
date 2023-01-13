package com.viktoriiaroi.core.network.model.company

import com.google.gson.annotations.SerializedName

data class LinksDTO(
    @SerializedName("website") var website: String? = null,
    @SerializedName("flickr") var flickr: String? = null,
    @SerializedName("twitter") var twitter: String? = null,
    @SerializedName("elon_twitter") var elonTwitter: String? = null,
)
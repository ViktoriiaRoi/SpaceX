package com.viktoriiaroi.core.network.model.news

import com.google.gson.annotations.SerializedName

data class LinksDTO(
    @SerializedName("article") val article: String? = null,
)
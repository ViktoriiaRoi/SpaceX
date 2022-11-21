package com.viktoriiaroi.core.network.model.news

import com.google.gson.annotations.SerializedName

data class NewsDTO(
    @SerializedName("title") val title: String? = null,
    @SerializedName("event_date_unix") val event_date_unix: Int? = null,
    @SerializedName("details") val details: String? = null,
    @SerializedName("links") val links: LinksDTO? = LinksDTO(),
)
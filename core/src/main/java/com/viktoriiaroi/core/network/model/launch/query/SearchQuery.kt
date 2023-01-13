package com.viktoriiaroi.core.network.model.launch.query

import com.google.gson.annotations.SerializedName

data class SearchQuery (
    @SerializedName("\$regex") var query: String,
    @SerializedName("\$options") var options: String,
)

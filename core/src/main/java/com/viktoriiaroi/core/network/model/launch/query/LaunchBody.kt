package com.viktoriiaroi.core.network.model.launch.query

import com.google.gson.annotations.SerializedName

data class LaunchBody(
    @SerializedName("query") var query: LaunchQuery,
    @SerializedName("options") var options: LaunchOptions,
) {
    companion object {
        fun requestAll(page: Int) = LaunchBody(LaunchQuery(),
            LaunchOptions(true, page, LaunchSort.ascSort))

        fun requestPast(page: Int) = LaunchBody(LaunchQuery(upcoming = false),
            LaunchOptions(true, page, LaunchSort.descSort))

        fun requestFuture(page: Int) = LaunchBody(LaunchQuery(upcoming = true),
            LaunchOptions(true, page, LaunchSort.ascSort))

        fun search(query: String) = LaunchBody(LaunchQuery(name = SearchQuery(query, "i")),
            LaunchOptions(false, null, LaunchSort.ascSort))
    }
}
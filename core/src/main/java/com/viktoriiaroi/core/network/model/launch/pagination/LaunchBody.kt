package com.viktoriiaroi.core.network.model.launch.pagination

import com.google.gson.annotations.SerializedName

data class LaunchBody(
    @SerializedName("query") var query: LaunchQuery,
    @SerializedName("options") var options: LaunchOptions,
) {
    companion object {
        fun requestAll(page: Int) =
            LaunchBody(LaunchQuery(), LaunchOptions(page, LaunchSort.ascSort))

        fun requestPast(page: Int) =
            LaunchBody(LaunchQuery(upcoming = false), LaunchOptions(page, LaunchSort.descSort))

        fun requestFuture(page: Int) =
            LaunchBody(LaunchQuery(upcoming = true), LaunchOptions(page, LaunchSort.ascSort))
    }
}
package com.viktoriiaroi.core.network.model.launch.query

import com.google.gson.annotations.SerializedName

data class LaunchSort(
    @SerializedName("flight_number") var sortByNumber: SortType,
) {
    companion object {
        val ascSort = LaunchSort(SortType.asc)
        val descSort = LaunchSort(SortType.desc)
    }
}

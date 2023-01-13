package com.viktoriiaroi.core.network.model.launch

import com.google.gson.annotations.SerializedName

data class LaunchDTO(
    @SerializedName("fairings") var fairings: FairingsDTO? = FairingsDTO(),
    @SerializedName("links") var links: LinksDTO? = LinksDTO(),
    @SerializedName("static_fire_date_utc") var staticFireDateUtc: String? = null,
    @SerializedName("static_fire_date_unix") var staticFireDateUnix: Int? = null,
    @SerializedName("tdb") var tdb: Boolean? = null,
    @SerializedName("net") var net: Boolean? = null,
    @SerializedName("window") var window: Int? = null,
    @SerializedName("rocket") var rocket: String? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("failures") var failures: List<FailuresDTO> = listOf(),
    @SerializedName("details") var details: String? = null,
    @SerializedName("crew") var crew: List<CrewDTO> = listOf(),
    @SerializedName("ships") var ships: List<String> = listOf(),
    @SerializedName("capsules") var capsules: List<String> = listOf(),
    @SerializedName("payloads") var payloads: List<String> = listOf(),
    @SerializedName("launchpad") var launchpad: String? = null,
    @SerializedName("auto_update") var autoUpdate: Boolean? = null,
    @SerializedName("flight_number") var flightNumber: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("date_utc") var dateUtc: String? = null,
    @SerializedName("date_unix") var dateUnix: Int? = null,
    @SerializedName("date_local") var dateLocal: String? = null,
    @SerializedName("date_precision") var datePrecision: String? = null,
    @SerializedName("upcoming") var upcoming: Boolean? = null,
    @SerializedName("cores") var cores: List<CoresDTO> = listOf(),
    @SerializedName("id") var id: String? = null,
)
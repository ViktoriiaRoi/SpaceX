package com.viktoriiaroi.core.network.model.rocket

import com.google.gson.annotations.SerializedName

data class RocketDTO(
    @SerializedName("height") var height: SizeDTO? = SizeDTO(),
    @SerializedName("diameter") var diameter: SizeDTO? = SizeDTO(),
    @SerializedName("mass") var mass: MassDTO? = MassDTO(),
    @SerializedName("flickr_images") var flickrImages: ArrayList<String> = arrayListOf(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("active") var active: Boolean? = null,
    @SerializedName("stages") var stages: Int? = null,
    @SerializedName("boosters") var boosters: Int? = null,
    @SerializedName("cost_per_launch") var costPerLaunch: Int? = null,
    @SerializedName("success_rate_pct") var successRatePct: Int? = null,
    @SerializedName("first_flight") var firstFlight: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("company") var company: String? = null,
    @SerializedName("wikipedia") var wikipedia: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("id") var id: String? = null,
)
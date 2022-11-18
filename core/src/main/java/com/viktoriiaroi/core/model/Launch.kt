package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.network.model.launch.LaunchDTO

data class Launch(
    val id: String,
    val name: String,
    val upcoming: Boolean,
    val success: Boolean,
    val number: Int,
    val details: String,
    val landingTypes: List<String>,
    val date: Int? = null,
    val datePrecision: DatePrecision,
    val imageUrl: String? = null,
) {
    companion object {
        fun fromDTO(src: LaunchDTO) = Launch(
            id = src.id.orEmpty(),
            name = src.name.orEmpty(),
            upcoming = src.upcoming ?: true,
            success = src.success ?: false,
            number = src.flightNumber ?: 0,
            details = src.details.orEmpty(),
            landingTypes = src.cores.mapNotNull { it.landingType },
            date = src.dateUnix,
            datePrecision = DatePrecision.valueOf(src.datePrecision?.uppercase() ?: "YEAR"),
            imageUrl = src.links?.patch?.small
        )
    }

}
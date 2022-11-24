package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.database.model.LaunchEntity
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
    val rocketId: String? = null,
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
            imageUrl = src.links?.patch?.small,
            rocketId = src.rocket,
        )

        fun fromEntity(src: LaunchEntity) = Launch(
            id = src.id,
            name = src.name,
            upcoming = src.upcoming,
            success = src.success,
            number = src.number,
            details = src.details,
            landingTypes = src.landingTypes,
            date = src.date,
            datePrecision = src.datePrecision,
            imageUrl = src.imageUrl
        )
    }
}
package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.database.model.RocketEntity
import com.viktoriiaroi.core.network.model.rocket.RocketDTO

data class Rocket(
    var id: String,
    val name: String,
    val description: String? = null,
    val type: String? = null,
    val active: Boolean? = null,
    val company: String? = null,
    val country: String? = null,
    val stages: Int? = null,
    val boosters: Int? = null,
    val height: Double? = null,
    val diameter: Double? = null,
    val mass: Int? = null,
) {
    companion object {
        fun fromDTO(src: RocketDTO) = Rocket(
            id = src.id.orEmpty(),
            name = src.name.orEmpty(),
            description = src.description,
            type = src.type,
            active = src.active,
            company = src.company,
            country = src.country,
            stages = src.stages,
            boosters = src.boosters,
            height = src.height?.meters,
            diameter = src.diameter?.meters,
            mass = src.mass?.kg
        )

        fun fromEntity(src: RocketEntity) = Rocket(
            id = src.id,
            name = src.name,
            description = src.description,
            type = src.type,
            active = src.active,
            company = src.company,
            country = src.country,
            stages = src.stages,
            boosters = src.boosters,
            height = src.height,
            diameter = src.diameter,
            mass = src.mass
        )
    }
}
package com.viktoriiaroi.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viktoriiaroi.core.model.Rocket

@Entity(tableName = "rockets")
data class RocketEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "type")
    val type: String? = null,
    @ColumnInfo(name = "active")
    val active: Boolean? = null,
    @ColumnInfo(name = "company")
    val company: String? = null,
    @ColumnInfo(name = "country")
    val country: String? = null,
    @ColumnInfo(name = "stages")
    val stages: Int? = null,
    @ColumnInfo(name = "boosters")
    val boosters: Int? = null,
    @ColumnInfo(name = "height")
    val height: Double? = null,
    @ColumnInfo(name = "diameter")
    val diameter: Double? = null,
    @ColumnInfo(name = "mass")
    val mass: Int? = null,
) {
    companion object {
        fun fromModel(src: Rocket) = RocketEntity(
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
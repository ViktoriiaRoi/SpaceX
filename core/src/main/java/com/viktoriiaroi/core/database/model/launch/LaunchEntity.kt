package com.viktoriiaroi.core.database.model.launch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.viktoriiaroi.core.database.converters.LaunchConverter
import com.viktoriiaroi.core.model.DatePrecision
import com.viktoriiaroi.core.network.model.launch.LaunchDTO

@TypeConverters(value = [LaunchConverter::class])
@Entity(tableName = "launches")
class LaunchEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "upcoming")
    val upcoming: Boolean,
    @ColumnInfo(name = "success")
    val success: Boolean,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "details")
    val details: String,
    @ColumnInfo(name = "landing_types")
    val landingTypes: List<String>,
    @ColumnInfo(name = "date")
    val date: Int? = null,
    @ColumnInfo(name = "date_precision")
    val datePrecision: DatePrecision,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,
    @ColumnInfo(name = "rocket_id")
    val rocketId: String? = null,
    @ColumnInfo(name = "core_id")
    val coreId: String? = null,
    @ColumnInfo(name = "core_flight")
    val coreFlight: Int? = null,
) {
    companion object {
        fun fromDTO(src: LaunchDTO) = LaunchEntity(
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
            coreId = src.cores[0].core,
            coreFlight = src.cores[0].flight
        )
    }
}
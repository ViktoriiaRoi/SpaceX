package com.viktoriiaroi.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.viktoriiaroi.core.database.converters.LaunchConverter
import com.viktoriiaroi.core.model.DatePrecision
import com.viktoriiaroi.core.model.Launch

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
) {
    companion object {
        fun fromModel(src: Launch) = LaunchEntity(
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
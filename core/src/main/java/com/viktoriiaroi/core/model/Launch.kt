package com.viktoriiaroi.core.model

import android.os.Parcelable
import com.viktoriiaroi.core.database.model.LaunchEntity
import kotlinx.parcelize.Parcelize

@Parcelize
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
    val coreId: String? = null,
    val coreFlight: Int? = null,
) : Parcelable {
    companion object {
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
            imageUrl = src.imageUrl,
            rocketId = src.rocketId,
            coreId = src.coreId,
            coreFlight = src.coreFlight
        )
    }
}
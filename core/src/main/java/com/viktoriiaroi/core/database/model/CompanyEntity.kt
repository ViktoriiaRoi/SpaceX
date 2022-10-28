package com.viktoriiaroi.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viktoriiaroi.core.model.Company

@Entity(tableName = "company")
class CompanyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "valuation")
    val valuation: String,
    @ColumnInfo(name = "employees")
    val employees: String,
    @ColumnInfo(name = "seo_link")
    val seoLink: String?,
    @ColumnInfo(name = "website_link")
    val websiteLink: String?,
) {
    companion object {
        fun fromModel(src: Company) = CompanyEntity(
            id = 0,
            description = src.description,
            valuation = src.valuation,
            employees = src.employees,
            seoLink = src.seoLink,
            websiteLink = src.websiteLink
        )
    }
}
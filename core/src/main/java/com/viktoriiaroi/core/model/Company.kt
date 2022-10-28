package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.database.model.CompanyEntity
import com.viktoriiaroi.core.network.model.company.CompanyDTO

data class Company(
    val description: String,
    val valuation: String,
    val employees: String,
    val seoLink: String?,
    val websiteLink: String?,
) {
    companion object {
        fun fromDTO(src: CompanyDTO) = Company(
            description = src.summary.orEmpty(),
            valuation = src.valuation.orEmpty(),
            employees = src.employees.toString(),
            seoLink = src.links?.elonTwitter,
            websiteLink = src.links?.website
        )

        fun fromEntity(src: CompanyEntity) = Company(
            description = src.description,
            valuation = src.valuation,
            employees = src.employees,
            seoLink = src.seoLink,
            websiteLink = src.websiteLink
        )
    }
}
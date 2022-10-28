package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.CompanyDao
import com.viktoriiaroi.core.database.model.CompanyEntity
import com.viktoriiaroi.core.network.CompanyService
import com.viktoriiaroi.core.model.Company
import com.viktoriiaroi.core.utils.processResponse
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val companyService: CompanyService,
    private val companyDao: CompanyDao,
) {
    suspend fun getCompanyFromNetwork(): Result<Company> =
        processResponse(
            { companyService.getCompany() },
            { Company.fromDTO(it) }
        )

    suspend fun getCompanyFromDatabase(): Result<Company> =
        kotlin.runCatching {
            Company.fromEntity(companyDao.getCompany())
        }

    suspend fun insertCompanyToDatabase(company: Company) {
        companyDao.insertCompany(CompanyEntity.fromModel(company))
    }
}

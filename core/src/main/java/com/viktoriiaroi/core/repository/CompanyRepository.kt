package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.CompanyDao
import com.viktoriiaroi.core.database.model.CompanyEntity
import com.viktoriiaroi.core.model.Company
import com.viktoriiaroi.core.network.CompanyService
import com.viktoriiaroi.core.utils.processResponse
import com.viktoriiaroi.core.utils.processSingleValue
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val companyService: CompanyService,
    private val companyDao: CompanyDao,
) {

    suspend fun getCompany(): Result<Company> {
        val networkResult = getCompanyFromNetwork()
        if (networkResult.isSuccess) {
            insertCompanyToDatabase(networkResult.getOrNull())
            return networkResult
        }
        val databaseResult = getCompanyFromDatabase()
        if (databaseResult.isSuccess) {
            return databaseResult
        }
        return networkResult
    }

    private suspend fun getCompanyFromNetwork(): Result<Company> =
        processResponse(
            { companyService.getCompany() },
            { Company.fromDTO(it) }
        )

    private suspend fun getCompanyFromDatabase(): Result<Company> =
        companyDao.getCompany().processSingleValue { Company.fromEntity(it) }

    private suspend fun insertCompanyToDatabase(company: Company?) {
        company?.let {
            companyDao.insertCompany(CompanyEntity.fromModel(it))
        }
    }

}

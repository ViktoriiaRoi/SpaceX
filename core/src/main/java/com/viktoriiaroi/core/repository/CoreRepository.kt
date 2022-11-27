package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.CoreDao
import com.viktoriiaroi.core.database.model.CoreEntity
import com.viktoriiaroi.core.model.Core
import com.viktoriiaroi.core.network.CoreService
import com.viktoriiaroi.core.utils.processNetworkResult
import com.viktoriiaroi.core.utils.processResponse
import com.viktoriiaroi.core.utils.processSingleValue
import javax.inject.Inject

class CoreRepository @Inject constructor(
    private val coreService: CoreService,
    private val coreDao: CoreDao,
) {
    suspend fun getCore(id: String): Result<Core> =
        getCoreFromNetwork(id).processNetworkResult(::insertCoreToDatabase) {
            getCoreFromDatabase(id)
        }

    private suspend fun getCoreFromNetwork(id: String): Result<Core> =
        processResponse(
            { coreService.getCore(id) },
            { Core.fromDTO(it) }
        )

    private suspend fun getCoreFromDatabase(id: String): Result<Core> =
        coreDao.getCore(id).processSingleValue { Core.fromEntity(it) }

    private suspend fun insertCoreToDatabase(core: Core?) {
        core?.let {
            coreDao.insertCore(CoreEntity.fromModel(it))
        }
    }
}

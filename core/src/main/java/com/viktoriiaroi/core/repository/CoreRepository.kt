package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.model.Core
import com.viktoriiaroi.core.network.CoreService
import com.viktoriiaroi.core.utils.processResponse
import javax.inject.Inject

class CoreRepository @Inject constructor(
    private val coreService: CoreService,
) {
    suspend fun getCore(id: String): Result<Core> =
        getCoreFromNetwork(id)

    private suspend fun getCoreFromNetwork(id: String): Result<Core> =
        processResponse(
            { coreService.getCore(id) },
            { Core.fromDTO(it) }
        )
}

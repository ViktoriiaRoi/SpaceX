package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.RocketDao
import com.viktoriiaroi.core.database.model.RocketEntity
import com.viktoriiaroi.core.model.Rocket
import com.viktoriiaroi.core.network.RocketService
import com.viktoriiaroi.core.utils.processNetworkResult
import com.viktoriiaroi.core.utils.processResponse
import com.viktoriiaroi.core.utils.processSingleValue
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val rocketService: RocketService,
    private val rocketDao: RocketDao,
) {
    suspend fun getRocket(id: String): Result<Rocket> =
        getRocketFromNetwork(id).processNetworkResult(::insertRocketToDatabase) {
            getRocketFromDatabase(id)
        }

    private suspend fun getRocketFromNetwork(id: String): Result<Rocket> =
        processResponse(
            { rocketService.getRocket(id) },
            { Rocket.fromDTO(it) }
        )

    private suspend fun getRocketFromDatabase(id: String): Result<Rocket> =
        rocketDao.getRocket(id).processSingleValue { Rocket.fromEntity(it) }

    private suspend fun insertRocketToDatabase(rocket: Rocket?) {
        rocket?.let {
            rocketDao.insertRocket(RocketEntity.fromModel(it))
        }
    }

}

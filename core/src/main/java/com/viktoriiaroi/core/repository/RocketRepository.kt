package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.model.Rocket
import com.viktoriiaroi.core.network.RocketService
import com.viktoriiaroi.core.utils.processResponse
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val rocketService: RocketService,
) {
    suspend fun getRocket(id: String): Result<Rocket> =
        getRocketFromNetwork(id)

    private suspend fun getRocketFromNetwork(id: String): Result<Rocket> =
        processResponse(
            { rocketService.getRocket(id) },
            { Rocket.fromDTO(it) }
        )
}

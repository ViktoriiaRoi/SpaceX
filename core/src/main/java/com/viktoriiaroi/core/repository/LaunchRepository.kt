package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.core.network.LaunchService
import com.viktoriiaroi.core.network.model.launch.LaunchDTO
import com.viktoriiaroi.core.utils.processResponse
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val launchService: LaunchService,
) {
    suspend fun getAllLaunches(): Result<List<Launch>> {
        return getAllLaunchesFromNetwork()
    }

    suspend fun getPastLaunches(): Result<List<Launch>> {
        return getPastLaunchesFromNetwork()
    }

    suspend fun getFutureLaunches(): Result<List<Launch>> {
        return getFutureLaunchesFromNetwork()
    }

    private fun launchMapper(list: List<LaunchDTO>) = list.map { Launch.fromDTO(it) }

    private suspend fun getAllLaunchesFromNetwork(): Result<List<Launch>> =
        processResponse(
            { launchService.getAllLaunches() },
            ::launchMapper)

    private suspend fun getPastLaunchesFromNetwork(): Result<List<Launch>> =
        processResponse(
            { launchService.getPastLaunches() },
            ::launchMapper)

    private suspend fun getFutureLaunchesFromNetwork(): Result<List<Launch>> =
        processResponse(
            { launchService.getFutureLaunches() },
            ::launchMapper)
}

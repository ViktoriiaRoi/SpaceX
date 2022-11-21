package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.LaunchDao
import com.viktoriiaroi.core.database.model.LaunchEntity
import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.core.network.LaunchService
import com.viktoriiaroi.core.network.model.launch.LaunchDTO
import com.viktoriiaroi.core.utils.processNetworkResult
import com.viktoriiaroi.core.utils.processList
import com.viktoriiaroi.core.utils.processResponse
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val launchService: LaunchService,
    private val launchDao: LaunchDao,
) {
    suspend fun getAllLaunches(): Result<List<Launch>> =
        getAllLaunchesFromNetwork().processNetworkResult(
            ::insertLaunchesToDatabase,
            ::getAllLaunchesFromDatabase)

    suspend fun getPastLaunches(): Result<List<Launch>> =
        getPastLaunchesFromNetwork().processNetworkResult(
            ::insertLaunchesToDatabase,
            ::getPastLaunchesFromDatabase)

    suspend fun getFutureLaunches(): Result<List<Launch>> =
        getFutureLaunchesFromNetwork().processNetworkResult(
            ::insertLaunchesToDatabase,
            ::getFutureLaunchesFromDatabase)

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

    private suspend fun getAllLaunchesFromDatabase(): Result<List<Launch>> =
        launchDao.getAllLaunches().processList { Launch.fromEntity(it) }

    private suspend fun getPastLaunchesFromDatabase(): Result<List<Launch>> =
        launchDao.getPastLaunches().processList { Launch.fromEntity(it) }

    private suspend fun getFutureLaunchesFromDatabase(): Result<List<Launch>> =
        launchDao.getFutureLaunches().processList { Launch.fromEntity(it) }

    private suspend fun insertLaunchesToDatabase(launchList: List<Launch>?) {
        launchList?.let {
            launchDao.insertLaunches(launchList.map { LaunchEntity.fromModel(it) })
        }
    }
}

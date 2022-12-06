package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.LaunchDao
import com.viktoriiaroi.core.database.model.LaunchEntity
import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.core.network.LaunchService
import com.viktoriiaroi.core.network.model.launch.LaunchDTO
import com.viktoriiaroi.core.utils.processList
import com.viktoriiaroi.core.utils.processResponse
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val launchService: LaunchService,
    private val launchDao: LaunchDao,
) {
    val launchFlow =
        launchDao.getLaunches().map { list -> list.processList { Launch.fromEntity(it) } }

    suspend fun updateAllLaunches() {
        getAllLaunchesFromNetwork().onSuccess { insertLaunchesToDatabase(it) }
    }

    suspend fun updatePastLaunches() {
        getPastLaunchesFromNetwork().onSuccess { insertLaunchesToDatabase(it) }
    }

    suspend fun updateFutureLaunches() {
        getFutureLaunchesFromNetwork().onSuccess { insertLaunchesToDatabase(it) }
    }

    private suspend fun getAllLaunchesFromNetwork(): Result<List<LaunchDTO>> =
        processResponse({ launchService.getAllLaunches() }, { it })

    private suspend fun getPastLaunchesFromNetwork(): Result<List<LaunchDTO>> =
        processResponse({ launchService.getPastLaunches() }, { it })

    private suspend fun getFutureLaunchesFromNetwork(): Result<List<LaunchDTO>> =
        processResponse({ launchService.getFutureLaunches() }, { it })

    private suspend fun insertLaunchesToDatabase(launchList: List<LaunchDTO>?) {
        launchList?.let {
            launchDao.insertLaunches(launchList.map { LaunchEntity.fromDTO(it) })
        }
    }
}

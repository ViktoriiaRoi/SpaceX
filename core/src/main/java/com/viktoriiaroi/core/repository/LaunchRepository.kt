package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.LaunchDao
import com.viktoriiaroi.core.database.model.launch.AllLaunchID
import com.viktoriiaroi.core.database.model.launch.FutureLaunchID
import com.viktoriiaroi.core.database.model.launch.LaunchEntity
import com.viktoriiaroi.core.database.model.launch.PastLaunchID
import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.core.network.LaunchService
import com.viktoriiaroi.core.network.model.launch.LaunchDTO
import com.viktoriiaroi.core.utils.processList
import com.viktoriiaroi.core.utils.processResponse
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val launchService: LaunchService,
    private val launchDao: LaunchDao,
) {
    val allLaunchFlow = launchDao.getAllLaunches().map(::mapper).distinctUntilChanged()
    val pastLaunchFlow = launchDao.getPastLaunches().map(::mapper).distinctUntilChanged()
    val futureLaunchFlow = launchDao.getFutureLaunches().map(::mapper).distinctUntilChanged()

    private fun mapper(list: List<LaunchEntity>) = list.processList { Launch.fromEntity(it) }

    suspend fun updateAllLaunches() {
        getAllLaunchesFromNetwork().onSuccess {
            insertLaunches(it)
            insertAllIDs(it)
        }
    }

    suspend fun updatePastLaunches() {
        getPastLaunchesFromNetwork().onSuccess {
            insertLaunches(it)
            insertPastIDs(it)
        }
    }

    suspend fun updateFutureLaunches() {
        getFutureLaunchesFromNetwork().onSuccess {
            insertLaunches(it)
            insertFutureIds(it)
        }
    }

    private suspend fun getAllLaunchesFromNetwork(): Result<List<LaunchDTO>> =
        processResponse({ launchService.getAllLaunches() }, { it })

    private suspend fun getPastLaunchesFromNetwork(): Result<List<LaunchDTO>> =
        processResponse({ launchService.getPastLaunches() }, { it })

    private suspend fun getFutureLaunchesFromNetwork(): Result<List<LaunchDTO>> =
        processResponse({ launchService.getFutureLaunches() }, { it })

    private suspend fun insertLaunches(launchList: List<LaunchDTO>) {
        launchDao.insertLaunches(launchList.map { LaunchEntity.fromDTO(it) })
    }

    private suspend fun insertAllIDs(launchList: List<LaunchDTO>) {
        launchDao.insertAllIDs(launchList.map { AllLaunchID(it.id.orEmpty()) })
    }

    private suspend fun insertPastIDs(launchList: List<LaunchDTO>) {
        launchDao.insertPastIDs(launchList.map { PastLaunchID(it.id.orEmpty()) })
    }

    private suspend fun insertFutureIds(launchList: List<LaunchDTO>) {
        launchDao.insertFutureIDs(launchList.map { FutureLaunchID(it.id.orEmpty()) })
    }
}

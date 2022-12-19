package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.LaunchDao
import com.viktoriiaroi.core.database.LaunchIDsDao
import com.viktoriiaroi.core.database.model.launch.AllLaunchID
import com.viktoriiaroi.core.database.model.launch.FutureLaunchID
import com.viktoriiaroi.core.database.model.launch.LaunchEntity
import com.viktoriiaroi.core.database.model.launch.PastLaunchID
import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.core.network.LaunchService
import com.viktoriiaroi.core.network.model.launch.LaunchDTO
import com.viktoriiaroi.core.network.model.launch.pagination.LaunchBody
import com.viktoriiaroi.core.network.model.launch.pagination.LaunchPage
import com.viktoriiaroi.core.utils.processList
import com.viktoriiaroi.core.utils.processResponse
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val launchService: LaunchService,
    private val launchDao: LaunchDao,
    private val idDao: LaunchIDsDao,
) {
    private fun mapper(list: List<LaunchEntity>) = list.processList { Launch.fromEntity(it) }

    val allLaunchFlow = launchDao.getAllLaunches().map(::mapper).distinctUntilChanged()
    val pastLaunchFlow = launchDao.getPastLaunches().map(::mapper).distinctUntilChanged()
    val futureLaunchFlow = launchDao.getFutureLaunches().map(::mapper).distinctUntilChanged()

    suspend fun updateAllLaunches(page: Int) =
        getAllLaunchesFromNetwork(page).updateLaunches(
            if (page == 1) ::clearAndInsertAllIDs
            else ::insertAllIDs
        )

    suspend fun updatePastLaunches(page: Int) =
        getPastLaunchesFromNetwork(page).updateLaunches(
            if (page == 1) ::clearAndInsertPastIDs
            else ::insertPastIDs
        )

    suspend fun updateFutureLaunches(page: Int) =
        getFutureLaunchesFromNetwork(page).updateLaunches(
            if (page == 1) ::clearAndInsertFutureIDs
            else ::insertFutureIDs
        )

    private suspend fun Result<LaunchPage>.updateLaunches(
        insertIDs: suspend (List<LaunchDTO>) -> Unit,
    ): Int? {
        var nextPage: Int? = null
        onSuccess {
            insertLaunches(it.docs)
            insertIDs(it.docs)
            nextPage = it.nextPage
        }
        return nextPage
    }

    private suspend fun getAllLaunchesFromNetwork(page: Int): Result<LaunchPage> =
        processResponse({ launchService.getLaunches(LaunchBody.requestAll(page)) }, { it })

    private suspend fun getPastLaunchesFromNetwork(page: Int): Result<LaunchPage> =
        processResponse({ launchService.getLaunches(LaunchBody.requestPast(page)) }, { it })

    private suspend fun getFutureLaunchesFromNetwork(page: Int): Result<LaunchPage> =
        processResponse({ launchService.getLaunches(LaunchBody.requestFuture(page)) }, { it })

    private suspend fun insertLaunches(launchList: List<LaunchDTO>) {
        launchDao.insertLaunches(launchList.map { LaunchEntity.fromDTO(it) })
    }

    private fun allIDsMapper(src: LaunchDTO) = AllLaunchID(src.id.orEmpty())
    private fun pastIDsMapper(src: LaunchDTO) = PastLaunchID(src.id.orEmpty())
    private fun futureIDsMapper(src: LaunchDTO) = FutureLaunchID(src.id.orEmpty())

    private suspend fun insertAllIDs(launchList: List<LaunchDTO>) {
        idDao.insertAllIDs(launchList.map(::allIDsMapper))
    }

    private suspend fun insertPastIDs(launchList: List<LaunchDTO>) {
        idDao.insertPastIDs(launchList.map(::pastIDsMapper))
    }

    private suspend fun insertFutureIDs(launchList: List<LaunchDTO>) {
        idDao.insertFutureIDs(launchList.map(::futureIDsMapper))
    }

    private suspend fun clearAndInsertAllIDs(launchList: List<LaunchDTO>) {
        idDao.clearAndInsertAllIDs(launchList.map(::allIDsMapper))
    }

    private suspend fun clearAndInsertPastIDs(launchList: List<LaunchDTO>) {
        idDao.clearAndInsertPastIDs(launchList.map(::pastIDsMapper))
    }

    private suspend fun clearAndInsertFutureIDs(launchList: List<LaunchDTO>) {
        idDao.clearAndInsertFutureIDs(launchList.map(::futureIDsMapper))
    }
}

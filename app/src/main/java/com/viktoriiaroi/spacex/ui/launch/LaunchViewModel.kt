package com.viktoriiaroi.spacex.ui.launch

import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.core.repository.LaunchRepository
import com.viktoriiaroi.spacex.ui.common.BaseViewModel
import com.viktoriiaroi.spacex.ui.launch.LaunchReducers.Companion.reduce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(private val launchRepo: LaunchRepository) :
    BaseViewModel<LaunchState, LaunchIntent>() {
    private var launchType = MutableStateFlow(LaunchType.ALL)

    init {
        handleIntent(LaunchIntent.LoadLaunches(LaunchType.ALL))
        viewModelScope.launch {
            setupAllFlow()
        }
        viewModelScope.launch {
            setupPastFlow()
        }
        viewModelScope.launch {
            setupFutureFlow()
        }
    }

    private suspend fun setupAllFlow() {
        launchRepo.allLaunchFlow.combine(launchType) { launches, type -> Pair(launches, type) }
            .collect {
                if (it.second == LaunchType.ALL) {
                    postResult(it.first)
                }
            }
    }

    private suspend fun setupPastFlow() {
        launchRepo.pastLaunchFlow.combine(launchType) { launches, type -> Pair(launches, type) }
            .collect {
                if (it.second == LaunchType.PAST) {
                    postResult(it.first)
                }
            }
    }

    private suspend fun setupFutureFlow() {
        launchRepo.futureLaunchFlow.combine(launchType) { launches, type -> Pair(launches, type) }
            .collect {
                if (it.second == LaunchType.FUTURE) {
                    postResult(it.first)
                }
            }
    }

    private fun postResult(result: Result<List<Launch>>) {
        mState.postValue(result.reduce())
    }

    override fun handleIntent(intent: LaunchIntent) {
        viewModelScope.launch {
            when (intent) {
                is LaunchIntent.LoadLaunches -> loadLaunches(intent.launchType)
            }
        }
    }

    private suspend fun loadLaunches(newType: LaunchType) {
        launchType.value = newType
        when (newType) {
            LaunchType.ALL -> launchRepo.updateAllLaunches()
            LaunchType.PAST -> launchRepo.updatePastLaunches()
            LaunchType.FUTURE -> launchRepo.updateFutureLaunches()
        }
    }
}
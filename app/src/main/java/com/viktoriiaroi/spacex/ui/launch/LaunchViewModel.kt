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
            updateState()
        }
    }

    private suspend fun updateState() {
        launchRepo.launchFlow.combine(launchType) { launches, type -> Pair(launches, type) }
            .collect { pair ->
                var result = pair.first
                result = when (pair.second) {
                    LaunchType.ALL -> result.getAllLaunches()
                    LaunchType.PAST -> result.getPastLaunches()
                    LaunchType.FUTURE -> result.getFutureLaunches()
                }
                mState.postValue(result.reduce())
            }
    }

    private fun Result<List<Launch>>.getAllLaunches() = map { list ->
        list.sortedBy { launch -> launch.number }
    }

    private fun Result<List<Launch>>.getPastLaunches() = map { list ->
        list.filter { launch -> !launch.upcoming }
            .sortedByDescending { launch -> launch.number }
    }

    private fun Result<List<Launch>>.getFutureLaunches() = map { list ->
        list.filter { launch -> launch.upcoming }
            .sortedBy { launch -> launch.number }
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
package com.viktoriiaroi.spacex.ui.launch

import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.repository.LaunchRepository
import com.viktoriiaroi.spacex.ui.common.BaseViewModel
import com.viktoriiaroi.spacex.ui.launch.LaunchReducers.Companion.reduce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(private val launchRepo: LaunchRepository) :
    BaseViewModel<LaunchState, LaunchIntent>() {
    init {
        handleIntent(LaunchIntent.LoadAllLaunches)
    }

    override fun handleIntent(intent: LaunchIntent) {
        viewModelScope.launch {
            when (intent) {
                is LaunchIntent.LoadAllLaunches -> loadAllLaunches()
                is LaunchIntent.LoadPastLaunches -> loadPastLaunches()
                is LaunchIntent.LoadFutureLaunches -> loadFutureLaunches()
            }
        }
    }

    private suspend fun loadAllLaunches() {
        mState.postValue(LaunchState.Loading)
        val result = launchRepo.getAllLaunches().map { list -> list.sortedBy { it.number } }
        mState.postValue(result.reduce())
    }

    private suspend fun loadPastLaunches() {
        mState.postValue(LaunchState.Loading)
        val result =
            launchRepo.getPastLaunches().map { list -> list.sortedByDescending { it.number } }
        mState.postValue(result.reduce())
    }

    private suspend fun loadFutureLaunches() {
        mState.postValue(LaunchState.Loading)
        val result = launchRepo.getFutureLaunches().map { list -> list.sortedBy { it.number } }
        mState.postValue(result.reduce())
    }
}
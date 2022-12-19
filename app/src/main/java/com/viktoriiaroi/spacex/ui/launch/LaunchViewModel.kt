package com.viktoriiaroi.spacex.ui.launch

import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.repository.LaunchRepository
import com.viktoriiaroi.spacex.ui.common.BaseViewModel
import com.viktoriiaroi.spacex.ui.launch.LaunchReducers.Companion.reduce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LaunchViewModel @Inject constructor(private val launchRepo: LaunchRepository) :
    BaseViewModel<LaunchState, LaunchIntent>() {

    private var typeFlow = MutableStateFlow(LaunchType.ALL)
    private var launchesFlow = typeFlow.flatMapLatest { type ->
        mState.postValue(LaunchState.LoadingFirst)
        typeChanged = true
        loadLaunches(1)
        getFlowByType(type)
    }

    private var nextPage: Int? = null
    private var typeChanged = false
    val isTypeChanged = typeChanged.also {
        typeChanged = false
    }

    init {
        handleIntent(LaunchIntent.ChangeType(LaunchType.ALL))
        viewModelScope.launch(Dispatchers.Default) {
            launchesFlow.collect {
                mState.postValue(it.reduce())
            }
        }
    }

    private fun getFlowByType(type: LaunchType) =
        when (type) {
            LaunchType.ALL -> launchRepo.allLaunchFlow
            LaunchType.PAST -> launchRepo.pastLaunchFlow
            LaunchType.FUTURE -> launchRepo.futureLaunchFlow
        }

    override fun handleIntent(intent: LaunchIntent) {
        viewModelScope.launch(Dispatchers.Default) {
            when (intent) {
                is LaunchIntent.ChangeType -> typeFlow.emit(intent.launchType)
                is LaunchIntent.LoadNextPage -> loadNextPage()
            }
        }
    }

    private suspend fun loadNextPage() {
        nextPage?.let {
            mState.postValue(LaunchState.LoadingNext)
            loadLaunches(it)
        }
    }

    private suspend fun loadLaunches(page: Int) {
        nextPage = when (typeFlow.value) {
            LaunchType.ALL -> launchRepo.updateAllLaunches(page)
            LaunchType.PAST -> launchRepo.updatePastLaunches(page)
            LaunchType.FUTURE -> launchRepo.updateFutureLaunches(page)
        }
    }
}
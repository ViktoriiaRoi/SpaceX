package com.viktoriiaroi.spacex.ui.launch

import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.spacex.ui.common.BaseState

sealed class LaunchState : BaseState {
    object LoadingFirst : LaunchState()
    object LoadingNext : LaunchState()
    data class ResultLaunches(val launches: List<Launch>) : LaunchState()
    data class Error(val throwable: Throwable) : LaunchState()
}

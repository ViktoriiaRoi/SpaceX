package com.viktoriiaroi.spacex.ui.search

import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.spacex.ui.common.BaseState

sealed class SearchState : BaseState {
    object Loading : SearchState()
    data class ResultLaunches(val launches: List<Launch>) : SearchState()
    data class Error(val throwable: Throwable) : SearchState()
}

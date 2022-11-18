package com.viktoriiaroi.spacex.ui.launch

import com.viktoriiaroi.core.model.Launch

class LaunchReducers {
    companion object {
        fun Result<List<Launch>>.reduce(): LaunchState {
            return fold(
                { LaunchState.ResultLaunches(it) },
                { LaunchState.Error(it) }
            )
        }
    }
}
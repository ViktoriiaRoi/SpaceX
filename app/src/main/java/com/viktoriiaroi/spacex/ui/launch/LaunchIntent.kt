package com.viktoriiaroi.spacex.ui.launch

import com.viktoriiaroi.spacex.ui.common.BaseIntent

sealed class LaunchIntent: BaseIntent {
    object LoadAllLaunches : LaunchIntent()
    object LoadPastLaunches : LaunchIntent()
    object LoadFutureLaunches : LaunchIntent()
}

package com.viktoriiaroi.spacex.ui.launch

import com.viktoriiaroi.spacex.ui.common.BaseIntent

sealed class LaunchIntent: BaseIntent {
    class LoadLaunches(val launchType: LaunchType) : LaunchIntent()
}

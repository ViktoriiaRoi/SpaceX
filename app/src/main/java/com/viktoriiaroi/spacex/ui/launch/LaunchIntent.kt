package com.viktoriiaroi.spacex.ui.launch

import com.viktoriiaroi.spacex.ui.common.BaseIntent

sealed class LaunchIntent : BaseIntent {
    class ChangeType(val launchType: LaunchType) : LaunchIntent()
    object LoadNextPage : LaunchIntent()
}

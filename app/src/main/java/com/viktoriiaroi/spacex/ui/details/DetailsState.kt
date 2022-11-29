package com.viktoriiaroi.spacex.ui.details

import com.viktoriiaroi.core.model.Core
import com.viktoriiaroi.core.model.Launch
import com.viktoriiaroi.core.model.Rocket
import com.viktoriiaroi.spacex.ui.common.BaseState

sealed class DetailsState : BaseState {
    object Loading : DetailsState()
    data class ResultDetails(val rocket: Rocket, val core: Core) : DetailsState()
    data class Error(val throwable: Throwable) : DetailsState()
}

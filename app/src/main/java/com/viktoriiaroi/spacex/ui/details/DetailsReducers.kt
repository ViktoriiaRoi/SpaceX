package com.viktoriiaroi.spacex.ui.details

import com.viktoriiaroi.core.model.Core
import com.viktoriiaroi.core.model.Rocket

class DetailsReducers {
    companion object {
        fun reduce(rocketResult: Result<Rocket>, coreResult: Result<Core>): DetailsState {
            val rocket = rocketResult.getOrNull()
            val core = coreResult.getOrNull()
            return if (rocket != null && core != null) {
                DetailsState.ResultDetails(rocket, core)
            } else {
                DetailsState.Error(rocketResult.exceptionOrNull()
                    ?: coreResult.exceptionOrNull()
                    ?: Throwable())
            }
        }
    }
}
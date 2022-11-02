package com.viktoriiaroi.spacex.ui.about

import com.viktoriiaroi.core.model.Company
import com.viktoriiaroi.spacex.ui.common.BaseState

sealed class AboutState : BaseState {
    object Loading : AboutState()
    data class ResultCompany(val company: Company) : AboutState()
    data class Error(val throwable: Throwable) : AboutState()
}

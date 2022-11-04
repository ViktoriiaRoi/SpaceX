package com.viktoriiaroi.spacex.ui.about

import com.viktoriiaroi.spacex.ui.common.BaseIntent

sealed class AboutIntent: BaseIntent {
    object LoadCompany : AboutIntent()
}

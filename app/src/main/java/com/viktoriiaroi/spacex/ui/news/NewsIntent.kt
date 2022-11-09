package com.viktoriiaroi.spacex.ui.news

import com.viktoriiaroi.spacex.ui.common.BaseIntent

sealed class NewsIntent: BaseIntent {
    object LoadNews : NewsIntent()
}

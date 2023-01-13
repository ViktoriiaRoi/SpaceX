package com.viktoriiaroi.spacex.ui.news

import com.viktoriiaroi.core.model.Company
import com.viktoriiaroi.core.model.News
import com.viktoriiaroi.spacex.ui.common.BaseState

sealed class NewsState : BaseState {
    object Loading : NewsState()
    data class ResultNews(val news: List<News>) : NewsState()
    data class Error(val throwable: Throwable) : NewsState()
}

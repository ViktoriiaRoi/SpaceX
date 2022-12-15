package com.viktoriiaroi.spacex.ui.search

import com.viktoriiaroi.spacex.ui.common.BaseIntent

sealed class SearchIntent: BaseIntent {
    class Typing(val isEmpty: Boolean): SearchIntent()
    class SearchByQuery(val query: String) : SearchIntent()
    object SearchAgain: SearchIntent()
}

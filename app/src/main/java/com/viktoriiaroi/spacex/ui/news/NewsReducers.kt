package com.viktoriiaroi.spacex.ui.news

import com.viktoriiaroi.core.model.News

fun Result<List<News>>.reduce(): NewsState {
    return fold(
        { NewsState.ResultNews(it) },
        { NewsState.Error(it) }
    )
}
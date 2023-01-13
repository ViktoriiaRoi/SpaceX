package com.viktoriiaroi.spacex.ui.search

import com.viktoriiaroi.core.model.Launch

fun Result<List<Launch>>.reduce(): SearchState {
    return fold(
        { SearchState.ResultLaunches(it) },
        { SearchState.Error(it) }
    )
}
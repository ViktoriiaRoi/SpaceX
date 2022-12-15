package com.viktoriiaroi.spacex.ui.search

import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.repository.LaunchRepository
import com.viktoriiaroi.spacex.ui.common.BaseViewModel
import com.viktoriiaroi.spacex.ui.search.SearchReducers.Companion.reduce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val launchRepo: LaunchRepository) :
    BaseViewModel<SearchState, SearchIntent>() {
    private var lastQuery: String? = null

    init {
        showAllLaunches()
    }

    override fun handleIntent(intent: SearchIntent) {
        viewModelScope.launch {
            when (intent) {
                is SearchIntent.SearchByQuery -> searchLaunches(intent.query)
                is SearchIntent.SearchAgain -> lastQuery?.let { searchLaunches(it) }
                is SearchIntent.Typing -> handleTyping(intent.isEmpty)
            }
        }
    }

    private suspend fun searchLaunches(query: String) {
        if (query != lastQuery) {
            mState.postValue(SearchState.Loading)
            lastQuery = query
            mState.postValue(launchRepo.searchLaunches("%$query%").reduce())
        }
    }

    private fun handleTyping(isEmpty: Boolean) {
        if (isEmpty) {
            showAllLaunches()
        } else {
            mState.postValue(SearchState.Loading)
        }
    }

    private fun showAllLaunches() {
        handleIntent(SearchIntent.SearchByQuery(""))
    }
}
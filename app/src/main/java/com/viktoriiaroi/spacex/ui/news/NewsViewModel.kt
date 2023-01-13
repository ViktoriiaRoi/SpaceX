package com.viktoriiaroi.spacex.ui.news

import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.repository.NewsRepository
import com.viktoriiaroi.spacex.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepo: NewsRepository) :
    BaseViewModel<NewsState, NewsIntent>() {
    init {
        handleIntent(NewsIntent.LoadNews)
    }

    override fun handleIntent(intent: NewsIntent) {
        viewModelScope.launch {
            when (intent) {
                is NewsIntent.LoadNews -> loadNews()
            }
        }
    }

    private suspend fun loadNews() {
        mState.postValue(NewsState.Loading)
        val result = newsRepo.getNews().map { list -> list.sortedByDescending { it.event_date } }
        mState.postValue(result.reduce())
}
}
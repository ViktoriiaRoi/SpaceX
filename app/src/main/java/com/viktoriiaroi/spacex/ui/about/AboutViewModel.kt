package com.viktoriiaroi.spacex.ui.about

import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.repository.CompanyRepository
import com.viktoriiaroi.spacex.ui.about.AboutReducers.Companion.reduce
import com.viktoriiaroi.spacex.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(private val companyRepo: CompanyRepository) :
    BaseViewModel<AboutState, AboutIntent>() {
    init {
        handleIntent(AboutIntent.LoadCompany)
    }

    override fun handleIntent(intent: AboutIntent) {
        viewModelScope.launch {
            when (intent) {
                is AboutIntent.LoadCompany -> loadCompany()
            }
        }
    }

    private suspend fun loadCompany() {
        mState.postValue(AboutState.Loading)
        mState.postValue(companyRepo.getCompany().reduce())
    }
}
package com.viktoriiaroi.spacex.ui.details

import androidx.lifecycle.viewModelScope
import com.viktoriiaroi.core.repository.CoreRepository
import com.viktoriiaroi.core.repository.RocketRepository
import com.viktoriiaroi.spacex.ui.common.BaseViewModel
import com.viktoriiaroi.spacex.ui.common.exception.AppException
import com.viktoriiaroi.spacex.ui.details.DetailsReducers.Companion.reduce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val rocketRepo: RocketRepository,
    private val coreRepo: CoreRepository,
) :
    BaseViewModel<DetailsState, DetailsIntent>() {
    override fun handleIntent(intent: DetailsIntent) {
        viewModelScope.launch {
            when (intent) {
                is DetailsIntent.LoadDetails -> loadDetails(intent.rocketId, intent.coreId)
            }
        }
    }

    private suspend fun loadDetails(rocketId: String?, coreId: String?) {
        mState.postValue(DetailsState.Loading)
        if (rocketId == null || coreId == null) {
            mState.postValue(DetailsState.Error(AppException.EmptyData))
        } else {
            val rocketResult = rocketRepo.getRocket(rocketId)
            val coreResult = coreRepo.getCore(coreId)
            mState.postValue(reduce(rocketResult, coreResult))
        }
    }
}
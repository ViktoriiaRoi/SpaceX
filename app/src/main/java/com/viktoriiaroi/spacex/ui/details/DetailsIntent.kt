package com.viktoriiaroi.spacex.ui.details

import com.viktoriiaroi.spacex.ui.common.BaseIntent

sealed class DetailsIntent: BaseIntent {
    class LoadDetails(val rocketId: String?, val coreId: String?) : DetailsIntent()
}

package com.viktoriiaroi.spacex.ui.about

import com.viktoriiaroi.core.model.Company


class AboutReducers {
    companion object {
        fun Result<Company>.reduce(): AboutState {
            return fold(
                { AboutState.ResultCompany(it) },
                { AboutState.Error(it) }
            )
        }
    }
}
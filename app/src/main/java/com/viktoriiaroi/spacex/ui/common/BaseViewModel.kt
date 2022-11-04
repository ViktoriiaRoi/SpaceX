package com.viktoriiaroi.spacex.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<STATE : BaseState, INTENT : BaseIntent> : ViewModel() {
    protected val mState = MutableLiveData<STATE>()
    val state: LiveData<STATE>
        get() {
            return mState
        }

    abstract fun handleIntent(intent: INTENT)
}
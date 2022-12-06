package com.viktoriiaroi.spacex.ui.common.exception

sealed class AppException(message: String) : Exception(message) {
    object EmptyData : AppException("Information not found")
}
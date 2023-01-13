package com.viktoriiaroi.core.network.exception

sealed class NetworkException(message: String) : Exception(message) {
    object EmptyData : NetworkException("Empty data")
    object Internet : NetworkException("No internet connection")
}
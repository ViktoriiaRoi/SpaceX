package com.viktoriiaroi.core.database.exception

sealed class DatabaseException(message: String) : Exception(message) {
    object EmptyData : DatabaseException("No data in database")
}
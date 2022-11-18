package com.viktoriiaroi.core.utils

suspend fun <T> Result<T>.processNetworkResult(
    insertFunc: suspend (T?) -> Unit,
    databaseFunc: suspend () -> Result<T>,
): Result<T> {
    if (isSuccess) {
        insertFunc(getOrNull())
        return this
    }
    val databaseResult = databaseFunc()
    if (databaseResult.isSuccess) {
        return databaseResult
    }
    return this
}
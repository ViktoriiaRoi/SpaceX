package com.viktoriiaroi.core.utils

import com.viktoriiaroi.core.database.exception.DatabaseException

fun <T, R> T?.processSingleValue(
    mapper: (T) -> R,
    exception: DatabaseException = DatabaseException.EmptyData
): Result<R> =
    this?.let {
        Result.success(mapper(it))
    } ?: Result.failure(exception)

fun <T, R> List<T>.processList(
    mapper: (T) -> R,
    exception: DatabaseException = DatabaseException.EmptyData
): Result<List<R>> =
    if (isNotEmpty()) {
        Result.success(map(mapper))
    } else {
        Result.failure(exception)
    }

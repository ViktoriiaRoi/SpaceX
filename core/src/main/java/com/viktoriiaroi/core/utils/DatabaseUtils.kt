package com.viktoriiaroi.core.utils

import com.viktoriiaroi.core.database.exception.DatabaseException

fun <T, R> T?.processSingleValue(
    mapper: (T) -> R,
): Result<R> =
    this?.let {
        Result.success(mapper(it))
    } ?: Result.failure(DatabaseException.EmptyData)

fun <T, R> List<T>.processList(
    mapper: (T) -> R,
): Result<List<R>> =
    if (isNotEmpty()) {
        Result.success(map(mapper))
    } else {
        Result.failure(DatabaseException.EmptyData)
    }

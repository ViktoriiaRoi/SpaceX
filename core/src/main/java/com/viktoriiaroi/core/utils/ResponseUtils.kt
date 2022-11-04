package com.viktoriiaroi.core.utils

import com.viktoriiaroi.core.network.exception.NetworkException
import retrofit2.Response
import java.net.UnknownHostException

suspend fun <T, R> processResponse(
    func: suspend () -> Response<T>,
    mapper: (T) -> R,
): Result<R> =
    try {
        func().toResult(mapper)
    } catch (e: UnknownHostException) {
        Result.failure(NetworkException.Internet)
    } catch (e: Exception) {
        Result.failure(e)
    }

private fun <T, R> Response<T>.toResult(mapper: (T) -> R): Result<R> {
    return if (this.isSuccessful) {
        this.body()?.let {
            Result.success(mapper(it))
        } ?: Result.failure(NetworkException.EmptyData)
    } else {
        Result.failure(Exception(this.message()))
    }
}
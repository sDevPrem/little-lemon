package com.example.littlelemon.data.model.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    object Loading : Result<Nothing>
    data class Failure(val e: Throwable? = null) : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .onStart { emit(Result.Loading) }
        .catch {
            it.printStackTrace()
            emit(Result.Failure(it))
        }
}
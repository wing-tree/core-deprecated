package com.wing.tree.bruni.core.useCase

sealed class Result<out R> {
    object Loading : Result<Nothing>()
    data class Success<out T: Any>(val data: T) : Result<T>()
    data class Failure(val throwable: Throwable) : Result<Nothing>()
}

inline fun <R: Any, T: Any> Result<T>.map(transform: (T) -> R): Result<R> {
    return when(this) {
        Result.Loading -> Result.Loading
        is Result.Success -> Result.Success(transform(data))
        is Result.Failure -> Result.Failure(throwable)
    }
}

inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(data)
    }

    return this
}

fun <T: Any> Result<T>.getOrDefault(defaultValue: T): T {
    return when(this) {
        Result.Loading -> defaultValue
        is Result.Success -> data
        is Result.Failure -> defaultValue
    }
}

fun <T: Any> Result<T>.getOrNull(): T? {
    return when(this) {
        Result.Loading -> null
        is Result.Success -> data
        is Result.Failure -> null
    }
}
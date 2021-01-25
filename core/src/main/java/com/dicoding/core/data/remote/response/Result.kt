package com.dicoding.core.data.remote.response

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: ErrorResponse = ErrorResponse()) : Result<Nothing>()
    data class Loading(val isLoading: Boolean) : Result<Nothing>()
}

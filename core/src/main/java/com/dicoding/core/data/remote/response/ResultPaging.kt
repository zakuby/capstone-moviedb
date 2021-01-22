package com.dicoding.core.data.remote.response

sealed class ResultPaging {
    data class Error(val error: ErrorResponse) : ResultPaging()
    data class Loading(val isLoading: Boolean) : ResultPaging()
    data class Empty(val isEmpty: Boolean) : ResultPaging()
}

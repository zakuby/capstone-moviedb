package com.dicoding.core.base

import com.dicoding.core.data.remote.response.ErrorResponse
import com.dicoding.core.data.remote.response.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRemoteDataSource {
    protected fun <T : Any> getFlowResult(call: suspend () -> Response<T>): Flow<Result<T>> = flow {
        try {
            emit(Result.Loading(true))
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    emit(Result.Success(body))
                } ?: emit(Result.Error(ErrorResponse(500, "Response is empty")))
            } else {
                val errorMsg =
                    if (response.errorBody().toString().isBlank()) "Something wrong happened. Please try again later."
                    else response.errorBody().toString()
                emit(Result.Error(ErrorResponse(response.code(), errorMsg)))
            }
            emit(Result.Loading(false))
        } catch (e: Exception) {
            emit(Result.Loading(false))
            e.printStackTrace()
            emit(Result.Error(ErrorResponse(500, "Something wrong happened. Please try again later.")))
        }
    }

    suspend fun <T : Any> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Result.Success(body)
                } ?: return Result.Error(ErrorResponse(500, "Response is empty"))
            } else {
                val errorMsg =
                    if (response.errorBody().toString().isBlank()) "Something wrong happened. Please try again later."
                    else response.errorBody().toString()
                return Result.Error(ErrorResponse(response.code(), errorMsg))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(ErrorResponse(500, "Something wrong happened. Please try again later."))
        }
    }
}
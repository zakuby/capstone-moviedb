package com.dicoding.core.data.remote

import com.dicoding.core.data.remote.response.ErrorResponse
import com.dicoding.core.data.remote.response.Result
import retrofit2.Response

object ApiCallHelper {
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

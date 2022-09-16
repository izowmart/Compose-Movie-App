package com.example.movieapp.feature_movieapp.data.repository

import com.example.movieapp.feature_movieapp.domain.model.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

open class BaseRepository {
    suspend fun <T> safeApiCall(api: suspend () -> T): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResponse.Success(api())
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        NetworkResponse.Failure(
                            isNetworkError = false,
                            errorCode = t.code(),
                            errorBody = t.response()?.errorBody()
                        )
                    }
                    else -> {
                        NetworkResponse.Failure(
                            isNetworkError = true,
                            errorCode = null,
                            errorBody = null
                        )
                    }
                }
            }
        }
    }


}
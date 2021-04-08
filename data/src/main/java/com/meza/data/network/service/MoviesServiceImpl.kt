package com.meza.data.network.service

import com.meza.domain.entity.Result
import com.meza.data.network.apis.MoviesApi
import com.meza.data.network.request.AuthRequest
import com.meza.data.network.response.*
import com.meza.domain.Constants.API_KEY
import com.meza.domain.Constants.ERROR_AUTH
import com.meza.domain.Constants.PASSWORD
import com.meza.domain.Constants.USER_NAME
import com.meza.domain.entity.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

class MoviesServiceImpl(private val moviesApi: MoviesApi): MoviesService {
    override suspend fun authenticate(authenticate: AuthRequest): Result<AuthResponse, Failure> {
        return if (authenticate.user.username == USER_NAME && authenticate.user.password == PASSWORD) {
            apiCall(call = { RESPONSE_MOCK_UP })
        } else {
            apiCall(call = { throw Throwable(ERROR_AUTH) })
        }
    }

    override suspend fun getMovieList(page: Int, language: String): Result<MovieListData, Failure> {
        return apiCall(call = { moviesApi.getMovieListAsync(API_KEY, language, page) })
    }

    override suspend fun getMovieDetail(movie_id: Int, language: String): Result<MovieData, Failure> {
        return apiCall(call = { moviesApi.getMovieDetailAsync(API_KEY, movie_id, language) })
    }

    private suspend inline fun <T : Any> apiCall(crossinline call: suspend () -> Response<T>): Result<T, Failure> {
        val response: Response<T>

        try {
            response = call.invoke()
        } catch (throwable: Throwable) {
            return Result.Error(parseException(throwable))
        }

        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(
                getErrorMessageFromServer(
                    response.errorBody()?.string()
                )
            )
        }
    }

    private suspend fun getErrorMessageFromServer(errorBody: String?) : Failure {
        return if (errorBody != null) {
            return withContext(Dispatchers.IO) {
                try {
                    val data = fromJson<ErrorResponse>(JSONObject(errorBody).toString())
                    when (data.status_code) {
                        401 -> {
                            return@withContext Failure.UnauthorizedOrForbidden(data.status_message)
                        }
                        403 -> {
                            return@withContext Failure.UnauthorizedOrForbidden(data.status_message)
                        }
                        500 -> {
                            return@withContext Failure.ServerError(data.status_message)
                        }
                        else -> {
                            return@withContext Failure.ServiceUncaughtFailure(data.status_message)
                        }
                    }
                } catch (e: Exception) {
                    return@withContext Failure.ServerBodyError(e.message.toString())
                }
            }
        } else {
            Failure.None
        }
    }

    private fun parseException(throwable: Throwable) : Failure {
        return when(throwable) {
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.ServiceUncaughtFailure(throwable.message?:"Service response doesn't match with response object.")
        }
    }
}
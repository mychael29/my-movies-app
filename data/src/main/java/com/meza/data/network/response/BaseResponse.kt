package com.meza.data.network.response

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class ServerResponse<T>(val result: T)
typealias AuthResponse = ServerResponse<AuthData>
typealias MovieResponse = ServerResponse<MovieListData>
typealias MovieDetailResponse = ServerResponse<MovieData>

inline fun <reified T> fromJson(json: String?): T = Gson().fromJson<T>(json, object: TypeToken<T>(){}.type)

data class ErrorResponse(
    val error: ErrorData
)

data class ErrorData(
    val code: Int,
    val userMessage: UserMessageData
)

data class UserMessageData(
    val es: String,
    val ja: String,
    val original: String
)



package com.meza.data.network.response

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

data class ServerResponse<T>(val result: T)
typealias AuthResponse = ServerResponse<AuthData>

inline fun <reified T> fromJson(json: String?): T = Gson().fromJson<T>(json, object: TypeToken<T>(){}.type)

data class ErrorResponse(
    val status_message: String,
    val success: Boolean? = null,
    val status_code: Int,
)

val RESPONSE_MOCK_UP: Response<ServerResponse<AuthData>> = Response.success(200,
    AuthResponse(
        AuthData(
            UserData("1", "Maycol", "Meza Roque", ProfileData("es"))
        )
    )
)



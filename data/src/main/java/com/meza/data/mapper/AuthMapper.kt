package com.meza.data.mapper

import com.meza.data.network.request.AuthRequest
import com.meza.data.network.response.AuthData
import com.meza.domain.entity.Authenticate
import com.meza.domain.entity.Authenticator

interface AuthMapper {
    fun mapResponse(response: AuthData): Authenticator
    fun mapRequest(entity: Authenticate): AuthRequest
}
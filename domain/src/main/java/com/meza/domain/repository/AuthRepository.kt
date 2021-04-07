package com.meza.domain.repository

import com.meza.domain.entity.Failure
import com.meza.domain.entity.Authenticator
import com.meza.domain.entity.Authenticate
import com.meza.domain.entity.Result

interface AuthRepository {
    suspend fun authenticate(authenticate: Authenticate): Result<Authenticator, Failure>
}
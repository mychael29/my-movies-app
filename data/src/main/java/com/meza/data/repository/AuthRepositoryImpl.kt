package com.meza.data.repository

import com.meza.data.mapper.AuthMapper
import com.meza.domain.repository.AuthRepository
import com.meza.domain.entity.Result
import com.meza.data.network.service.MoviesService
import com.meza.domain.entity.Failure
import com.meza.domain.entity.Authenticator
import com.meza.domain.entity.Authenticate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val service: MoviesService,
    private val mapper: AuthMapper
): AuthRepository {

    override suspend fun authenticate(authenticate: Authenticate): Result<Authenticator, Failure> {
        val authRequest =  mapper.mapRequest(authenticate)
        return when (val response  = service.authenticate(authRequest)) {
            is Result.Success -> Result.Success(mapper.mapResponse(response.result.result))
            is Result.Error -> Result.Error(response.error)
        }
    }

}
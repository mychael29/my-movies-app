package com.meza.domain.usecase.auth

import com.meza.domain.entity.Failure
import com.meza.domain.repository.AuthRepository
import com.meza.domain.entity.Authenticate
import com.meza.domain.entity.Authenticator
import com.meza.domain.entity.Result
import com.meza.domain.usecase.BaseUseCase

class GetAuthUseCase(private val repository: AuthRepository):  BaseUseCase<Authenticator, GetAuthUseCase.Params>() {

    override suspend fun run(params: Params): Result<Authenticator, Failure>
            = repository.authenticate(params.authenticate)

    data class Params(val authenticate: Authenticate)

}
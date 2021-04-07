package com.meza.data.mapper

import com.meza.data.network.request.*
import com.meza.data.network.response.AuthData
import com.meza.domain.entity.Authenticator
import com.meza.domain.entity.Authenticate
import com.meza.domain.entity.Profile
import com.meza.domain.entity.User

class AuthMapperImpl: AuthMapper {

    override fun mapResponse(response: AuthData): Authenticator {
        return Authenticator(
            user = User(
                response.user._id,
                response.user.name,
                response.user.lastName,
                Profile(response.user.profile.language)
            ),
            profile = Profile(response.user.profile.language)
        )
    }

    override fun mapRequest(entity: Authenticate): AuthRequest {
        return AuthRequest(
            user =  UserRequest(
                _id = "",
                username = entity.username,
                password = entity.password,
                profile = ProfileRequest(
                    entity.profile.language
                ),
            )
        )
    }
}
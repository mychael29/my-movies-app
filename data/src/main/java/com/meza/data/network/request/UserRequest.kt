package com.meza.data.network.request

data class AuthRequest(
    val user: UserRequest
)

data class UserRequest(
    val _id: String,
    val username: String,
    val password: String,
    val profile: ProfileRequest
)

data class ProfileRequest(
    val language: String
)


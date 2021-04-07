package com.meza.data.network.response

data class AuthData(
    val user: UserData
)

data class UserData(
    val _id: String,
    val name: String,
    val lastName: String,
    val profile: ProfileData,
)

data class ProfileData(
    val language: String
)
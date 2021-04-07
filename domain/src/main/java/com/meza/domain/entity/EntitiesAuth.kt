package com.meza.domain.entity


data class Authenticator(
    val user: User,
    val profile: Profile
)

data class User(
    val _id: String? = null,
    val name: String? = null,
    val lastName: String? = null,
    val profile: Profile? = null
)

data class Profile(
    val language: String
)

data class Authenticate(
    val username: String,
    val password: String,
    val profile: Profile
)







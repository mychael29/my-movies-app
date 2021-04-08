package com.meza.domain


object Constants {
    const val URL_BASE = "https://api.themoviedb.org/3/"
    const val API_KEY = "3890bea2ca66e75fb5e19c858954ba3b"
    const val PREF_USER = "AUTH_MOVIES"
    const val LOGIN_REQUEST_CODE = 1
    const val USER_NAME = "Admin"
    const val PASSWORD = "Password*123"
    private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
    private const val IMAGE_SIZE_W342 = "w342"
    private const val IMAGE_SIZE_W185 = "w185"
    private const val IMAGE_SIZE_W154 = "w154"
    const val BASE_IMAGE_LARGE = BASE_IMAGE_URL + IMAGE_SIZE_W342
    const val BASE_IMAGE_MEDIUM = BASE_IMAGE_URL + IMAGE_SIZE_W185
    const val BASE_IMAGE_SMALL = BASE_IMAGE_URL + IMAGE_SIZE_W154
}
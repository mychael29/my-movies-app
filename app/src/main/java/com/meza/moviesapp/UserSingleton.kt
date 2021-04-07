package com.meza.moviesapp

import com.google.gson.Gson
import com.meza.data.SharedPreferenceDataSource
import com.meza.domain.Constants.PREF_USER
import com.meza.domain.entity.User

object UserSingleton {
    private var repository: SharedPreferenceDataSource? = null
    fun init(repository: SharedPreferenceDataSource) {
        UserSingleton.repository = repository
    }

    fun getUser(): User = Gson().fromJson(repository?.getString(PREF_USER, Gson().toJson(User())), User::class.java)
    fun setUser(user: User?) = repository?.putString(PREF_USER, Gson().toJson(user))
}
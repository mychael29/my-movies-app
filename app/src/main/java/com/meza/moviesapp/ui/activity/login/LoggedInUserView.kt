package com.meza.moviesapp.ui.activity.login

import com.meza.domain.entity.User

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayUser: User
    //... other data fields that may be accessible to the UI
)
package com.meza.moviesapp.ui.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.widget.EditText
import androidx.lifecycle.viewModelScope
import com.meza.domain.entity.*
import com.meza.moviesapp.UserSingleton
import com.meza.moviesapp.ui.BaseViewModel
import com.meza.moviesapp.ui.activity.splash.SplashViewModel
import com.meza.domain.usecase.auth.GetAuthUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
) : BaseViewModel<SplashViewModel>() {

    private val _loggedInUser = MutableLiveData<User>()
    val loggedInUser: LiveData<User> = _loggedInUser

    fun login(username: EditText, password: EditText) {
        showLoadingView(true)
        val authenticate = Authenticate(username.text.toString(), password.text.toString(), Profile("es"))
        getAuthUseCase.invoke(viewModelScope, GetAuthUseCase.Params(authenticate)) {
            it.result(::handleInitiate, ::handleNotInitiate)
        }
    }

    private fun handleInitiate(authenticator: Authenticator) {
        showLoadingView(false)
        showErrorCauseView(false)
        _loggedInUser.value = authenticator.user
        UserSingleton.setUser(authenticator.user)
    }

    private fun handleNotInitiate(failure: Failure) {
        showLoadingView(false)
        showErrorCauseView(true)
        handleUseCaseFailureFromBase(failure)
    }
}
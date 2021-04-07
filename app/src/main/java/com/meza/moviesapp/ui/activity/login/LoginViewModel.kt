package com.meza.moviesapp.ui.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.meza.moviesapp.R
import com.meza.moviesapp.UserSingleton
import com.meza.moviesapp.ui.BaseViewModel
import com.meza.moviesapp.ui.activity.splash.SplashViewModel
import com.meza.domain.entity.Failure
import com.meza.domain.entity.Authenticate
import com.meza.domain.entity.Authenticator
import com.meza.domain.entity.Profile
import com.meza.domain.usecase.auth.GetAuthUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
) : BaseViewModel<SplashViewModel>() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private fun handleInitiate(authenticator: Authenticator) {
        _loginResult.value =
            LoginResult(
                success = LoggedInUserView(
                    displayUser = authenticator.user
                )
            )
        UserSingleton.setUser(authenticator.user) // todo: estaria de mas porque no me pide cerrar sesión
    }

    private fun handleNotInitiate(failure: Failure) {
        _loginResult.value =
            LoginResult(
                error = handleUseCaseFailureFromInitiate(failure)
            )
    }

    fun login(username: String, password: String) {
        val authenticate = Authenticate(username, password, Profile("es"))
        getAuthUseCase.invoke(viewModelScope, GetAuthUseCase.Params(authenticate)) {
            it.result(::handleInitiate, ::handleNotInitiate)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(
                    usernameError = R.string.invalid_username
                )
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(
                    passwordError = R.string.invalid_password
                )
        } else {
            _loginForm.value =
                LoginFormState(
                    isDataValid = true
                )
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
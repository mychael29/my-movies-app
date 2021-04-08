package com.meza.moviesapp.ui.activity.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Patterns
import android.widget.EditText
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
import com.meza.moviesapp.model.FieldsModel
import com.meza.moviesapp.model.MovieModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
) : BaseViewModel<SplashViewModel>() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private fun handleInitiate(authenticator: Authenticator) {
        showLoadingView(false)
        _loginResult.value =
            LoginResult(
                success = LoggedInUserView(
                    displayUser = authenticator.user
                )
            )
        UserSingleton.setUser(authenticator.user) // todo: estaria de mas porque no me pide cerrar sesión
    }

    private fun handleNotInitiate(failure: Failure) {
        showLoadingView(false)
        _loginResult.value =
            LoginResult(
                error = handleUseCaseFailureFromInitiate(failure)
            )
    }

    fun login(username: EditText, password: EditText) {
        showLoadingView(true)
        val authenticate = Authenticate(username.text.toString(), password.text.toString(), Profile("es"))
        getAuthUseCase.invoke(viewModelScope, GetAuthUseCase.Params(authenticate)) {
            it.result(::handleInitiate, ::handleNotInitiate)
        }
    }
}
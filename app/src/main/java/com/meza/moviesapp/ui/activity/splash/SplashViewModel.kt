package com.meza.moviesapp.ui.activity.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meza.moviesapp.UserSingleton
import com.meza.moviesapp.ui.*
import com.meza.domain.entity.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: BaseViewModel<SplashViewModel>() {

    private val _dataState = MutableLiveData<StateInitiate>()
    val dataState: LiveData<StateInitiate> get() = _dataState

    fun initiateApp() {
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(2000)
            if (UserSingleton.getUser()._id == null) {
                handleNotInitiate(Failure.None)
            } else {
                handleInitiate()
            }
        }
        GlobalScope.launch(context = Dispatchers.Main) {
            println("launched coroutine 2")
        }
    }

    private fun handleInitiate() {
        _dataState.value =
            Initiate
    }

    private fun handleNotInitiate(failure: Failure) {
        _dataState.value =
            NotInitiate(
                handleUseCaseFailureFromInitiate(failure)
            )
    }

}
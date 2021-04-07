package com.meza.moviesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meza.moviesapp.util.ConnectionUtil
import com.meza.domain.entity.Failure

abstract class BaseViewModel<T> : ViewModel() {
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing : LiveData<Boolean>
        get() = _isRefreshing

    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _showRvMovieList = MutableLiveData(false)
    val showRvMovieList : LiveData<Boolean>
        get() = _showRvMovieList

    private val _showErrorCause = MutableLiveData(false)
    val showErrorCause : LiveData<Boolean>
        get() = _showErrorCause

    private val _errorCause = MutableLiveData<String>()
    val errorCause: LiveData<String>
        get() = _errorCause

    protected fun setRefreshingView(show: Boolean) {
        _isRefreshing.value = show
    }

    protected fun showLoadingView(show: Boolean) {
        _isLoading.value = show
    }

    protected fun showRvMovieListView(show: Boolean) {
        _showRvMovieList.value = show
    }

    protected fun showErrorCauseView(show: Boolean) {
        _showErrorCause.value = show
    }

    protected fun setError(cause: String) {
        _errorCause.value = cause
    }


    protected fun handleUseCaseFailureFromBase(failure: Failure) {
        if (ConnectionUtil.isNetworkAvailable()) {
            when(failure) {
                is Failure.UnauthorizedOrForbidden -> setError(failure.userMessage)
                is Failure.NetworkConnectionLostSuddenly -> setError("Connection lost suddenly. Check the wifi or mobile data.")
                is Failure.SSLError -> setError("WARNING: SSL Exception")
                is Failure.TimeOut -> setError("Time out.")
                is Failure.ServerBodyError -> setError(failure.message)
                is Failure.DataToDomainMapperFailure -> setError("Data to domain mapper failure: ${failure.mapperException}")
                is Failure.ServiceUncaughtFailure -> setError(failure.uncaughtFailureMessage)
                is Failure.ServerError -> setError(failure.message)
                is Failure.None -> setError("Load again.")
            }
        } else {
            setError("No network detected")
        }
    }

    protected fun handleUseCaseFailureFromInitiate(failure: Failure): String {
        return when (ConnectionUtil.isNetworkAvailable()) {
            true -> {
                when(failure) {
                    is Failure.UnauthorizedOrForbidden -> failure.userMessage
                    is Failure.NetworkConnectionLostSuddenly -> "Connection lost suddenly. Check the wifi or mobile data."
                    is Failure.SSLError -> "WARNING: SSL Exception"
                    is Failure.TimeOut -> "Time out."
                    is Failure.ServerBodyError -> failure.message
                    is Failure.DataToDomainMapperFailure -> "Data to domain mapper failure: ${failure.mapperException}"
                    is Failure.ServiceUncaughtFailure -> failure.uncaughtFailureMessage
                    is Failure.ServerError -> failure.message
                    is Failure.None -> "Load again."
                }
            }
            false ->  "No network detected"
        }
    }

    private fun verifyNetworkAvailable(): String {
        return when (ConnectionUtil.isNetworkAvailable()) {
            true -> "Load again."
            false ->  "No network detected"
        }
    }

}
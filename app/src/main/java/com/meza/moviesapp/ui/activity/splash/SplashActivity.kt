package com.meza.moviesapp.ui.activity.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meza.moviesapp.R
import com.meza.moviesapp.core.di.viewmodel.ViewModelFactory
import com.meza.moviesapp.ui.activity.login.LoginActivity
import com.meza.moviesapp.ui.activity.movie.MoviesActivity
import com.meza.moviesapp.util.extensions.launchActivity
import com.meza.domain.Constants.LOGIN_REQUEST_CODE
import dagger.android.AndroidInjection.inject
import javax.inject.Inject


class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelFactory
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        inject(this)

        viewModel = ViewModelProvider(this, providerFactory).get(SplashViewModel::class.java)

        observeViewState()

        viewModel.initiateApp()
    }

    private fun observeViewState() {
        viewModel.dataState.observe(this, Observer {
            when (it) {
                is Initiate -> loadMovies()
                is NotInitiate -> loadLogin()
            }
        })
    }

    private fun loadLogin() {
        launchActivity<LoginActivity>(LOGIN_REQUEST_CODE)
    }

    private fun loadMovies() {
        launchActivity<MoviesActivity>()
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadMovies()
        }
    }
}
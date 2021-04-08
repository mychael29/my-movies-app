package com.meza.moviesapp.ui.activity.login

import android.app.Activity
import androidx.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.meza.domain.entity.User
import com.meza.moviesapp.BR
import com.meza.moviesapp.R
import com.meza.moviesapp.databinding.ActivityLoginBinding
import com.meza.moviesapp.ui.BaseActivity

import dagger.android.AndroidInjection.inject

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun getBindingVariable(): Int = BR.loginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        viewModel.loggedInUser.observe(this, Observer {
            updateUiWithUser(it)
            setResult(Activity.RESULT_OK)
            finish()
        })
    }

    private fun updateUiWithUser(user: User) {
        val welcome = getString(R.string.welcome)
        val displayName = user.name
        Toast.makeText(applicationContext, "$welcome $displayName", Toast.LENGTH_LONG).show()
    }
}

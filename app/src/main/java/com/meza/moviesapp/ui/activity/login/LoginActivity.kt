package com.meza.moviesapp.ui.activity.login

import android.app.Activity
import androidx.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.meza.moviesapp.BR
import com.meza.moviesapp.R
import com.meza.moviesapp.databinding.ActivityLoginBinding
import com.meza.moviesapp.ui.BaseActivity

import com.meza.moviesapp.util.DialogUtil
import dagger.android.AndroidInjection.inject

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun getBindingVariable(): Int = BR.loginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        viewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer
            loginResult.error?.let { error ->
                showLoginFailed(error)
            }
            loginResult.success?.let { success ->
                updateUiWithUser(success)
                setResult(Activity.RESULT_OK)
                finish()
            }
        })
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayUser.name
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(errorString: String) {
        DialogUtil.showDialog(this, errorString)
    }
}

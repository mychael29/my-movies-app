package com.meza.moviesapp.ui.activity.login

import android.app.Activity
import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.meza.moviesapp.BR
import com.meza.moviesapp.R
import com.meza.moviesapp.databinding.ActivityLoginBinding
import com.meza.moviesapp.ui.BaseActivity

import com.meza.moviesapp.util.DialogUtil
import com.meza.moviesapp.util.extensions.afterTextChanged
import dagger.android.AndroidInjection.inject

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun getBindingVariable(): Int = BR.loginViewModel

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        inject(this)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })
        binding.username.afterTextChanged {
            loginViewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }
        binding.password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }
            binding.login.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                binding.apply {
                    loginViewModel?.login(username.text.toString(), password.text.toString())
                }
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayUser.name
        // TODO : initiate successful logged in experience
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
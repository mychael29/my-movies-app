package com.meza.moviesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meza.moviesapp.R
import com.meza.moviesapp.core.di.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection.inject
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<VM : ViewModel, B : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding: B
    private var progressDialog: AlertDialog? = null

    @Inject
    lateinit var providerFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        binding = getViewBinding()
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, providerFactory).get(getViewModelClass())
        binding.setVariable(getBindingVariable(), viewModel)
        binding.lifecycleOwner = this
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    abstract fun getViewBinding(): B

    abstract fun getBindingVariable(): Int
}
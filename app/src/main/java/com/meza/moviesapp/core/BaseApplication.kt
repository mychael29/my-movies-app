package com.meza.moviesapp.core

import com.meza.data.SharedPreferenceDataSource
import com.meza.moviesapp.core.di.DaggerAppComponent
import com.meza.moviesapp.UserSingleton
import com.meza.moviesapp.util.ConnectionUtil
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        UserSingleton.init(
            SharedPreferenceDataSource(
                applicationContext
            )
        )
        ConnectionUtil.init(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val androidInjector: AndroidInjector<BaseApplication> =
            DaggerAppComponent.builder().application(this).build()
        androidInjector.inject(this)
        return androidInjector
    }
}
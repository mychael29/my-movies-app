package com.meza.moviesapp.core.di

import com.meza.moviesapp.core.di.modules.AuthScope
import com.meza.moviesapp.core.di.viewmodel.ViewModelModule
import com.meza.moviesapp.ui.activity.login.LoginActivity
import com.meza.moviesapp.ui.activity.splash.SplashActivity
import com.meza.moviesapp.ui.activity.movie.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSplashActivity(): SplashActivity?

    @AuthScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeLoginActivity(): LoginActivity?

    @AuthScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMoviesActivity(): MoviesActivity?

}
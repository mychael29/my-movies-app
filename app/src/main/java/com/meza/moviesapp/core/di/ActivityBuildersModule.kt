package com.meza.moviesapp.core.di

import com.meza.moviesapp.core.di.modules.AuthScope
import com.meza.moviesapp.core.di.viewmodel.ViewModelModule
import com.meza.moviesapp.ui.activity.splash.SplashActivity
import com.meza.moviesapp.ui.activity.movie.MoviesActivity
import com.meza.moviesapp.ui.activity.movie.detail.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeAuthActivity(): SplashActivity?

    @AuthScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMoviesActivity(): MoviesActivity?

    @AuthScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity?

}
package com.meza.moviesapp.core.di

import com.meza.moviesapp.core.di.modules.AuthScope
import com.meza.moviesapp.core.di.viewmodel.ViewModelModule
import com.meza.moviesapp.ui.activity.movie.detail.MovieDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment?
}
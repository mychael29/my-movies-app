package com.meza.moviesapp.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meza.domain.repository.AuthRepository
import com.meza.moviesapp.mapper.MovieModelMapper
import com.meza.moviesapp.mapper.MovieModelMapperImpl
import com.meza.moviesapp.ui.activity.movie.MoviesViewModel
import com.meza.domain.repository.MoviesRepository
import com.meza.domain.usecase.auth.GetAuthUseCase
import com.meza.domain.usecase.movies.GetMoviesUseCase
import com.meza.moviesapp.ui.activity.login.LoginViewModel
import com.meza.moviesapp.ui.activity.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider


@Module
class ViewModelModule {
    @Provides
    fun provideViewModelFactory(providers: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory =
        ViewModelFactory(providers)

    @IntoMap
    @Provides
    @ViewModelKey(SplashViewModel::class)
    fun splashViewModel(): ViewModel = SplashViewModel()

    @IntoMap
    @Provides
    @ViewModelKey(LoginViewModel::class)
    fun authViewModel(repository: AuthRepository): ViewModel = LoginViewModel(
        GetAuthUseCase(
            repository
        )
    )

    @IntoMap
    @Provides
    @ViewModelKey(MoviesViewModel::class)
    fun moviesViewModel(repository: MoviesRepository, movieModelMapper: MovieModelMapper): ViewModel
            = MoviesViewModel(
        GetMoviesUseCase(
            repository
        ), movieModelMapper)

    @Provides
    fun provideMovieModelMapper(): MovieModelMapper = MovieModelMapperImpl()
}
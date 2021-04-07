package com.meza.moviesapp.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meza.moviesapp.mapper.MovieModelMapper
import com.meza.moviesapp.mapper.MovieModelMapperImpl
import com.meza.moviesapp.ui.activity.movie.MoviesViewModel
import com.meza.moviesapp.ui.activity.movie.detail.MovieDetailViewModel
import com.meza.domain.repository.MoviesRepository
import com.meza.domain.usecase.movies.GetMoviesUseCase
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
    @ViewModelKey(MoviesViewModel::class)
    fun moviesViewModel(repository: MoviesRepository, movieModelMapper: MovieModelMapper): ViewModel
            = MoviesViewModel(
        GetMoviesUseCase(
            repository
        ), movieModelMapper)

    @IntoMap
    @Provides
    @ViewModelKey(MovieDetailViewModel::class)
    fun movieDetailViewModel(): ViewModel = MovieDetailViewModel()

    @Provides
    fun provideMovieModelMapper(): MovieModelMapper = MovieModelMapperImpl()
}
package com.meza.moviesapp.ui.activity.movie

import androidx.lifecycle.*
import com.meza.moviesapp.UserSingleton
import com.meza.moviesapp.mapper.MovieModelMapper
import com.meza.moviesapp.model.MovieModel
import com.meza.moviesapp.ui.*
import com.meza.moviesapp.ui.adapter.MovieAdapter
import com.meza.domain.entity.Failure
import com.meza.domain.entity.Movie
import com.meza.domain.entity.MovieList
import com.meza.domain.usecase.movies.GetMoviesUseCase
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val mapper: MovieModelMapper,
) : BaseViewModel<MoviesViewModel>() {

    val touchListenerItem = MutableLiveData<MovieModel>()
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<MovieModel>> = _movieList.switchMap {
        liveData {
            emit(mapper.movieDomainToPresentation(it))
        }
    }

    val adapter = MovieAdapter(arrayListOf()) { _movieModel, _->
        touchListenerItem.value = _movieModel
    }

    fun initiate() {
        handleLoading()
        executeGetMovieListUseCase(1)
    }

    fun bindItemsAfterMapping(movieListMapped: List<MovieModel>, isNextPage: Boolean) {
        setRefreshingView(false)
        showLoadingView(false)
        showErrorCauseView(false)
        showRvMovieListView(true)
        adapter.addItems(movieListMapped, isNextPage)
    }

    private fun executeGetMovieListUseCase(page: Int) {
        getMoviesUseCase.invoke(viewModelScope, GetMoviesUseCase.Params(page, UserSingleton.getUser().profile!!.language)) {
            it.result(::handleUseCaseSuccess, ::handleUseCaseFailure)
        }
    }

    fun refreshData(page: Int) {
        setRefreshingView(true)
        executeGetMovieListUseCase(page)
    }

    private fun handleLoading() {
        showRvMovieListView(false)
        showLoadingView(true)
    }

    private fun handleUseCaseSuccess(movieList: MovieList) {
        _movieList.value = movieList.results
    }

    fun restoredMovieList(items: List<Movie>) {
        _movieList.value = items
    }

    fun saveStateMovieList(): List<Movie>? {
        return _movieList.value
    }

    private fun handleUseCaseFailure(failure: Failure) {
        handleUseCaseFailureFromBase(failure)
        showErrorCauseView(true)
        showLoadingView(false)
        setRefreshingView(false)
        showRvMovieListView(false)
    }

}
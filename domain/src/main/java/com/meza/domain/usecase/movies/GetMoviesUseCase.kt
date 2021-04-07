package com.meza.domain.usecase.movies

import com.meza.domain.entity.Failure
import com.meza.domain.entity.MovieList
import com.meza.domain.entity.Result
import com.meza.domain.repository.MoviesRepository
import com.meza.domain.usecase.BaseUseCase

class GetMoviesUseCase (private val repository: MoviesRepository):  BaseUseCase<MovieList, GetMoviesUseCase.Params>() {

    override suspend fun run(params: Params): Result<MovieList, Failure>
            = repository.getMovies(params.page, params.language)

    data class Params(val page: Int, val language: String)

}
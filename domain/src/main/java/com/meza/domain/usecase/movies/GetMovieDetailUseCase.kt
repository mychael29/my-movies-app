package com.meza.domain.usecase.movies

import com.meza.domain.entity.Failure
import com.meza.domain.entity.Movie
import com.meza.domain.entity.Result
import com.meza.domain.repository.MoviesRepository
import com.meza.domain.usecase.BaseUseCase

class GetMovieDetailUseCase(private val repository: MoviesRepository):  BaseUseCase<Movie, GetMovieDetailUseCase.Params>() {

    override suspend fun run(params: Params): Result<Movie, Failure>
            = repository.getMovieDetail(params.movie_id, params.language)

    data class Params(val movie_id: Int, val language: String)

}
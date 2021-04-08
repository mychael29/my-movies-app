package com.meza.data.repository

import com.meza.data.mapper.MoviesMapper
import com.meza.domain.entity.Result
import com.meza.data.network.service.MoviesService
import com.meza.domain.entity.Failure
import com.meza.domain.entity.*
import com.meza.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val service: MoviesService,
    private val mapper: MoviesMapper
): MoviesRepository {

    override suspend fun getMovies(page: Int, language: String): Result<MovieList, Failure> {
        return when (val response  = service.getMovieList(page, language)) {
            is Result.Success -> Result.Success(mapper.map(response.result))
            is Result.Error -> Result.Error(response.error)
        }
    }

    override suspend fun getMovieDetail(movie_id: Int, language: String): Result<Movie, Failure> {
        return when (val result  = service.getMovieDetail(movie_id, language)) {
            is Result.Success -> Result.Success(mapper.mapItem(result.result))
            is Result.Error -> Result.Error(result.error)
        }
    }

}
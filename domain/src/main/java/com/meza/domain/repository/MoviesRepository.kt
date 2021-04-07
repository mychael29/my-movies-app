package com.meza.domain.repository

import com.meza.domain.entity.Failure
import com.meza.domain.entity.Movie
import com.meza.domain.entity.MovieList
import com.meza.domain.entity.Result


interface MoviesRepository {

    suspend fun getMovies(page: Int, language: String): Result<MovieList, Failure>

    suspend fun getMovieDetail(movie_id: Int, language: String): Result<Movie, Failure>

}
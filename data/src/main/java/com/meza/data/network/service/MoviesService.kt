package com.meza.data.network.service

import com.meza.domain.entity.Result
import com.meza.data.network.request.AuthRequest
import com.meza.data.network.response.AuthResponse
import com.meza.data.network.response.MovieDetailResponse
import com.meza.data.network.response.MovieResponse
import com.meza.domain.entity.Failure

interface MoviesService {

    suspend fun authenticate(authenticate: AuthRequest): Result<AuthResponse, Failure>

    suspend fun getMovieList(page: Int, language: String): Result<MovieResponse, Failure>

    suspend fun getMovieDetail(movie_id: Int, language: String): Result<MovieDetailResponse, Failure>

}
package com.meza.data.network.apis

import com.meza.data.network.response.*
import retrofit2.Response
import retrofit2.http.*

interface MoviesApi {

    @GET("movie/upcoming")
    fun getMovieListAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetailAsync(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Response<MovieDetailResponse>

}
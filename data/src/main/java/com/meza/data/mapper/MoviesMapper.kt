package com.meza.data.mapper

import com.meza.data.network.response.MovieData
import com.meza.data.network.response.MovieListData
import com.meza.domain.entity.Movie
import com.meza.domain.entity.MovieList

interface MoviesMapper {
    fun map(response: MovieListData): MovieList
    fun map(response: MovieData): Movie
}
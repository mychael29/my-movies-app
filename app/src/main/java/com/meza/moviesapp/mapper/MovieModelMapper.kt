package com.meza.moviesapp.mapper

import com.meza.moviesapp.model.MovieModel
import com.meza.domain.entity.Movie

interface MovieModelMapper {

    suspend fun movieDomainToPresentation(items: List<Movie>) : List<MovieModel>

}
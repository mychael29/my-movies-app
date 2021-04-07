package com.meza.moviesapp.mapper

import com.meza.moviesapp.model.MovieModel
import com.meza.domain.entity.Movie

class MovieModelMapperImpl: MovieModelMapper {

    override suspend fun movieDomainToPresentation(items: List<Movie>): List<MovieModel> {
        return items.map {
            MovieModel(itemMovie = it, isCancelled = false)
        }
    }

}
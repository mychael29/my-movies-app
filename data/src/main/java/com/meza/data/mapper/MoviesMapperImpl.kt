package com.meza.data.mapper

import com.meza.data.network.response.*
import com.meza.domain.entity.MovieList
import com.meza.domain.entity.Movie

class MoviesMapperImpl: MoviesMapper {
    private fun mapItemList(responseList: List<MovieData>): List<Movie> {
        return responseList.map { (map(it)) }
    }

    override fun map(response: MovieListData): MovieList {
        return MovieList(
            results = mapItemList(response.results),
            page = response.page,
            total_pages = response.total_pages,
            total_results = response.total_results
        )
    }

    override fun map(response: MovieData): Movie {
        return Movie(
            id = response.id,
            title = response.title,
            adult = response.adult,
            backdrop_path = response.backdrop_path,
            genre_ids = response.genre_ids,
            original_language = response.original_language,
            original_title = response.original_title,
            overview = response.overview,
            popularity = response.popularity,
            poster_path = response.poster_path,
            release_date = response.release_date,
            video = response.video,
            vote_average = response.vote_average,
            vote_count = response.vote_count
        )
    }
}
package com.meza.domain.entity

import java.io.Serializable

data class MovieList(
    val results: List<Movie>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
)

data class Movie(
    val id : Int,
    val video : Boolean,
    val vote_count : Int? = 0,
    val vote_average : Double? = 0.0,
    val popularity : Double? = 0.0,
    val title : String? = "",
    val release_date : String? = "",
    val original_language : String? = "",
    val original_title : String? = "",
    val genre_ids : List<Int>,
    val backdrop_path : String? = "",
    val adult : Boolean,
    val overview : String,
    val poster_path : String? = ""
): Serializable


package com.meza.data.network.response

data class MovieListData(
    val results: List<MovieData>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
)

data class MovieData(
    val id : Int,
    val video : Boolean,
    val vote_count : Int,
    val vote_average : Double,
    val popularity : Double,
    val title : String,
    val release_date : String = "",
    val original_language : String,
    val original_title : String,
    val genre_ids : List<Int>,
    val backdrop_path : String,
    val adult : Boolean,
    val overview : String,
    val poster_path : String
)

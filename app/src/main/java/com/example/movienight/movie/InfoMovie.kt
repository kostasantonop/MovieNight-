package com.example.movienight.movie

data class InfoMovie(
    val poster_path : String?,
    val title : String,
    val original_title: String,
    val vote_average : Double?,
    val vote_count: Int?,
    val overview : String?,
    var release_date: String?,
    var favourite : Boolean = false
)

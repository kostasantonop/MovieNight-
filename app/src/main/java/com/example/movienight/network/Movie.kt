package com.example.movienight.network

data class Movie(
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Double,
)

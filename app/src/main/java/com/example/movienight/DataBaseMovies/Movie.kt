package com.example.movienight.DataBaseMovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class Movie(
    @PrimaryKey val id: Int,
    val poster_path : String?,
    val title : String,
    val vote_average : Double?,
    var release_date: String?,
    var favourite : Boolean = false
)

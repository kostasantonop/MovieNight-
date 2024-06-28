package com.example.movienight.DataBaseMovies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movienight.DataBaseMovies.movie.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
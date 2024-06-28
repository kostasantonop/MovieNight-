package com.example.movienight.DataBaseMovies

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movienight.DataBaseMovies.movie.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM favourite_movies")
    suspend fun getAllFavourites(): MutableList<Movie>

    @Query("SELECT * FROM favourite_movies WHERE id = :id LIMIT 1")
    suspend fun getSelectedMovie(id:Int) : Movie?
}
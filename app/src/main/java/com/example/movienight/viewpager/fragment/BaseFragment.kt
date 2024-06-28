package com.example.movienight.viewpager.fragment

import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.movienight.DataBaseMovies.MovieDao
import com.example.movienight.DataBaseMovies.MovieDatabase

abstract class BaseFragment : Fragment() {
    lateinit var movieDao: MovieDao

    fun setupDatabase(){
        val database =  Room.databaseBuilder(
            requireContext(),
            MovieDatabase::class.java,
            "Database_Movie"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        movieDao = database.getMovieDao()
    }
}
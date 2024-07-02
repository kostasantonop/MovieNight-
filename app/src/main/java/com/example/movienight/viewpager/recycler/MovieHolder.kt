package com.example.movienight.viewpager.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.DataBaseMovies.MovieDao
import com.example.movienight.DataBaseMovies.movie.Movie
import com.example.movienight.databinding.HolderRecyclerViewBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking

class MovieViewHolder(val binding: HolderRecyclerViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, movieDao: MovieDao) {
        binding.apply {
            if (movie.poster_path != null) {
                Picasso.get().load("https://image.tmdb.org/t/p/w500".plus(movie.poster_path))
                    .resize(0, 620)
                    .into(binding.imageInfoBtn)
            }
            binding.titleTextView.text = movie.title
            binding.dateTextView.text = movie.release_date?.take(4)
            if (movie.vote_average == 0.0) {
                binding.ratingTextView.text = "Too few votes!"
                binding.imageViewStar.visibility = View.GONE
            } else {
                binding.ratingTextView.text = String.format("%.1f", movie.vote_average)
                binding.imageViewStar.visibility = View.VISIBLE
            }

            runBlocking {
                checkboxFavorite.isChecked = movieDao.getSelectedMovie(movie.id) != null
            }

            checkboxFavorite.setOnClickListener {
                runBlocking {
                    if (checkboxFavorite.isChecked) {
                        movie.favourite = true
                        movieDao.addMovie(movie)
                    } else {
                        movie.favourite = false
                        movieDao.deleteMovie(movie)

                    }
                }
            }
        }
    }
}

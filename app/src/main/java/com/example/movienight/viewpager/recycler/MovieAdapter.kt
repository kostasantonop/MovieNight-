package com.example.movienight.viewpager.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.HolderRecyclerViewBinding
import com.example.movienight.DataBaseMovies.Movie
import com.example.movienight.DataBaseMovies.MovieDao


class MovieAdapter(private var movies: List<Movie>,
                   private val listener: (String) -> Unit,
                   private var movieDao: MovieDao,
                   ) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind( movies[position],movieDao)
        holder.binding.infoBtn.setOnClickListener {
            listener(movies[position].id.toString())
        }
    }

}
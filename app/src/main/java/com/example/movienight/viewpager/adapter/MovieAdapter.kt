package com.example.movienight.viewpager.adapter

import Movie
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.HolderRecyclerViewBinding
import com.squareup.picasso.Picasso


class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder( val binding : HolderRecyclerViewBinding ) :RecyclerView.ViewHolder(binding.root){
        fun bind(movie : Movie){
            binding.apply {

                Picasso.get().load("https://image.tmdb.org/t/p/w500".plus(movie.poster_path)).into(imageView)

                titleTextView.text = movie.title
                ratingTextView.text = movie.vote_average.toString()
                infoBtn.setOnClickListener {

                    if (itemView.context == null){ return@setOnClickListener}
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setMessage(movie.overview)
                    builder.setTitle(movie.title)
                    builder.setCancelable(false)

                    builder.setPositiveButton("Close ") { dialog, which ->
                        dialog.dismiss()
                        // Handle positive button click if needed
                    }

                    val alertDialog = builder.create()
                    alertDialog.show()
                }
                checkboxFavorite.isChecked = movie.favourite
                checkboxFavorite.setOnClickListener {
                    if (checkboxFavorite.isChecked){
                    movie.favourite = true} else{
                        movie.favourite = false
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = movies[position]
        holder.bind(movieItem)
    }
}
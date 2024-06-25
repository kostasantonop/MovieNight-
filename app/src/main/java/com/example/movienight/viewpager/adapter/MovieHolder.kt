package com.example.movienight.viewpager.adapter

import Movie
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.HolderRecyclerViewBinding
import com.squareup.picasso.Picasso

class MovieViewHolder( val binding : HolderRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(movie : Movie){
        binding.apply {
            if (movie.poster_path != null) {
                Picasso.get().load("https://image.tmdb.org/t/p/w500".plus(movie.poster_path)).resize(0,620)
                    .into(binding.imageView)
            }
            binding.titleTextView.text = movie.title
            binding.dateTextView.text = movie.release_date?.take(4)
            if(movie.vote_average == 0.0){
                binding.ratingTextView.text = "Too few votes!"
                binding.imageViewStar.visibility = View.GONE
            }else {
                binding.ratingTextView.text = String.format("%.1f", movie.vote_average)
                binding.imageViewStar.visibility = View.VISIBLE
            }

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
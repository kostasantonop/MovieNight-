package com.example.movienight.viewpager.tab1recyclerview

import Movie
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.R
import com.example.movienight.databinding.AlertDialogInfoMovieBinding
import com.example.movienight.databinding.HolderRecyclerViewBinding


class MovieAdapter( private val context: Context, private val movies : MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder( private val binding : HolderRecyclerViewBinding ) :RecyclerView.ViewHolder(binding.root){
        fun bind(movie : Movie){



            binding.apply {
                //imageView  //TODO πως θα φορτωθει η εικονα
                titleTextView.text = movie.title
                ratingTextView.text = movie.rate.toString()
                infoBtn.setOnClickListener {

                    if (itemView.context == null){ return@setOnClickListener}
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setMessage(movie.info)
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
        return MovieViewHolder(HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = movies[position]
        holder.bind(movieItem)
    }
}
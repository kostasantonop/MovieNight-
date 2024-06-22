package com.example.movienight.viewpager.tab1recyclerview

import Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.HolderRecyclerViewBinding

class MovieAdapter(private val movies : MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding : HolderRecyclerViewBinding ) :RecyclerView.ViewHolder(binding.root){
        fun bind(movie : Movie){
            binding.apply {
                //imageView  //TODO
                titleTextView.text = movie.title
                ratingTextView.text = movie.rate.toString()
                infoBtn.setOnClickListener {
                    //εδω θα ανοιγει παραθυρο ισως alerter με τα info
                }
                checkboxFavorite.isChecked = movie.favourite
                checkboxFavorite.setOnClickListener {
                    if (checkboxFavorite.isChecked){
                    movie.favourite = true}   //το favourite το χουμε αρχικοποιησει false. σε καθε πατημα αν ελεγεχεται αν ειναι τσεκ και αν ειναι θα γινεται true
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
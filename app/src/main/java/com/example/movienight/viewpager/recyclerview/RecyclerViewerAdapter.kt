package com.example.movienight.viewpager.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.HolderRecyclerViewBinding
import com.example.movienight.network.Movie
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(
    private val dataList: List<Movie>
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size // Set the actual number of items
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.binding.titleTextView.text = dataList[position].title
        holder.binding.ratingTextView.text = String.format("%.1f", dataList[position].vote_average)
        Picasso.get().load("https://image.tmdb.org/t/p/w500".plus(dataList[position].poster_path)).fit().centerCrop().into(holder.binding.imageView)
    }
}


package com.example.movienight.viewpager.tab1recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.HolderRecyclerViewBinding

class RecyclerViewAdapter(private val fragment: Fragment) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 4 // Set the actual number of items
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Bind data to the views
    }
}


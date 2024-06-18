package com.example.movienight.viewpager.tab1recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.databinding.HolderRecyclerViewBinding

class RecyclerViewAdapter(private val fragment: Tab1RecyclerViewFragment) : RecyclerView.Adapter<RecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
    }

}
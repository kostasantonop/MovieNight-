package com.example.movienight.viewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movienight.databinding.FragmentTab1RecyclerViewBinding
import com.example.movienight.viewpager.recyclerview.RecyclerViewAdapter

class Tab1RecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentTab1RecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab1RecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tab1RecyclerView.adapter = RecyclerViewAdapter()

    }
}
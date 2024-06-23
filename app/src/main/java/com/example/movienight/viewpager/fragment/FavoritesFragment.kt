package com.example.movienight.viewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movienight.databinding.FragmetFavoritesBinding

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmetFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmetFavoritesBinding.inflate(inflater)
        return binding.root
    }

}
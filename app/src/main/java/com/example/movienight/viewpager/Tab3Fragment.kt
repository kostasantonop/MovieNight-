package com.example.movienight.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movienight.databinding.FragmentTab3Binding

class Tab3Fragment : Fragment() {

    private lateinit var binding: FragmentTab3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab3Binding.inflate(inflater)
        return binding.root
    }

}
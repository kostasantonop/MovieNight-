package com.example.movienight.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movienight.databinding.FragmentTab2RecyclerViewBinding
import com.example.movienight.viewpager.tab1recyclerview.RecyclerViewAdapter

class Tab2RecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentTab2RecyclerViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab2RecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            binding.tab2RecyclerView.adapter = RecyclerViewAdapter(this)
        }
    }

}
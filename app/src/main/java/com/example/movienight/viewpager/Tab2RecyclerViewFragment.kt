package com.example.movienight.viewpager

import Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.databinding.FragmentTab2RecyclerViewBinding
import com.example.movienight.viewpager.tab1recyclerview.MovieAdapter

class Tab2RecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentTab2RecyclerViewBinding
    private lateinit var movieSearchResultAdapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab2RecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val empty_movieList_for_Testing = mutableListOf<Movie>()
        var moviestatswars = Movie("poster", "Starwars", 8.2,"mia tainia me polemo sto galaxia")

        empty_movieList_for_Testing.add(moviestatswars)

        movieSearchResultAdapter = MovieAdapter(requireContext(),empty_movieList_for_Testing)


        binding.tab2RecyclerView.adapter = movieSearchResultAdapter
        binding.tab2RecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

}
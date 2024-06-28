package com.example.movienight.viewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movienight.MovieViewModel
import com.example.movienight.databinding.FragmentPopularBinding
import com.example.movienight.viewpager.recycler.MovieAdapter

class PopularFragment : BaseFragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val act = activity
        movieViewModel = if (act != null) {
            ViewModelProvider(act).get(MovieViewModel::class.java)
        } else {
            ViewModelProvider(this).get(MovieViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDatabase()
        movieViewModel.setupDatabase()
    }

    override fun onResume() {
        super.onResume()

        movieViewModel.popularMovies()

        movieViewModel.movies1.observe(viewLifecycleOwner, Observer { dataList ->
            binding.tab1RecyclerView.adapter =
                MovieAdapter(movies = dataList, listener = { value ->
                    movieViewModel.itemSelected(value)
                },movieDao)
        })
    }
}

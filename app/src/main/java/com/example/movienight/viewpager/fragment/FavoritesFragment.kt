package com.example.movienight.viewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.DataBaseMovies.Movie
import com.example.movienight.MovieViewModel
import com.example.movienight.databinding.FragmetFavoritesBinding
import com.example.movienight.viewpager.recycler.MovieAdapter
import kotlinx.coroutines.runBlocking

class FavoritesFragment : BaseFragment() {

    private lateinit var binding: FragmetFavoritesBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

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
        binding = FragmetFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDatabase()
        movieViewModel.setupDatabase()


        //allagi tessera
        binding.tab3RecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize movieAdapter with an empty mutable list
        movieAdapter = MovieAdapter(mutableListOf(), { movieId ->
            movieViewModel.itemSelected(movieId)
        }, movieDao)

        binding.tab3RecyclerView.adapter = movieAdapter
    }

    override fun onResume() {
        super.onResume()

        movieViewModel.favouriteMovies()

        movieViewModel.movies4.observe(viewLifecycleOwner, Observer{dataList ->
            binding.tab3RecyclerView.adapter =
                MovieAdapter(movies = dataList, listener = { value ->
                    movieViewModel.itemSelected(value)},movieDao)
                })
    }

}
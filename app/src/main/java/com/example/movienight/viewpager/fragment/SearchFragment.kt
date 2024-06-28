package com.example.movienight.viewpager.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movienight.DataBaseMovies.Movie
import com.example.movienight.MovieViewModel
import com.example.movienight.databinding.FragmentSearchBinding
import com.example.movienight.viewpager.recycler.MovieAdapter

class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding
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
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDatabase()
        movieViewModel.setupDatabase()
    }

    override fun onResume() {
        super.onResume()

        binding.searchView.setOnClickListener {
            binding.searchView.onActionViewExpanded()
            binding.searchView.layoutParams.height = binding.searchView.height
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                movieViewModel.searchMovies(query.toString().replace(" ", "%20"))
                movieViewModel.movies2.observe(viewLifecycleOwner, Observer { dataList ->
                    binding.tab2RecyclerView.adapter =
                        MovieAdapter(movies = dataList, listener = { value ->
                            movieViewModel.itemSelected(value)
                        },movieDao)
                })
                hideKeyboard()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.tab2RecyclerView.adapter =
            MovieAdapter(movies = mutableListOf(), listener = { value ->
                movieViewModel.itemSelected(value)
            }, movieDao)
    }

    override fun onPause() {
        super.onPause()

        binding.tab2RecyclerView.adapter =
            MovieAdapter(movies = mutableListOf(), listener = { value ->
                movieViewModel.itemSelected(value)
            },movieDao)
    }

    private fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
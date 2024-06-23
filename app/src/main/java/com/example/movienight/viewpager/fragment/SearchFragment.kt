package com.example.movienight.viewpager.fragment

import Movie
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.MovieList
import com.example.movienight.databinding.FragmentTab2RecyclerViewBinding
import com.example.movienight.viewpager.adapter.MovieAdapter
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentTab2RecyclerViewBinding
    private lateinit var movieSearchResultAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab2RecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = HttpClient(CIO)
        var searchedMovie: String? = null

        binding.textInputLayout.setEndIconOnClickListener(
            View.OnClickListener {
                val searchedMovie: String = binding.textInputEditText.text.toString()
                searchMovies(client, searchedMovie)
                hideKeyboard()
                binding.textInputEditText.text = null
            }
        )
    }

    private fun searchMovies(client: HttpClient, searchedMovie: String?) {
        runBlocking {
            val searchList: MutableList<Movie> = mutableListOf()
            //TODO: Get more than one page
            val response =
                client.get("https://api.themoviedb.org/3/movie/popular?api_key=5272d12fcd7c9ef1b93f5ff8af93a411")

            val jsonResponse = Gson().fromJson(response.bodyAsText(), MovieList::class.java)

            if (searchedMovie.isNullOrEmpty()) {
                searchList.addAll(jsonResponse.results)
            } else {
                for (movie in jsonResponse.results) {
                    if (movie.title.contains(searchedMovie, ignoreCase = true)) {
                        searchList.add(movie)
                    }
                }
            }
            movieSearchResultAdapter = MovieAdapter(requireContext(), searchList)
            binding.tab2RecyclerView.adapter = movieSearchResultAdapter
            binding.tab2RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
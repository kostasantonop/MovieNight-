package com.example.movienight.viewpager.fragment

import com.example.movienight.movie.Movie
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.movie.MovieList
import com.example.movienight.databinding.FragmentSearchBinding
import com.example.movienight.viewpager.recycler.MovieAdapter
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var movieSearchResultAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = HttpClient(CIO)

        binding.searchView.setOnClickListener {
            binding.searchView.onActionViewExpanded()
            binding.searchView.layoutParams.height = binding.searchView.height
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMovies(client, query.toString().replace(" ", "%20"))
                //TODO(figure out why keyboard closes upon clicking the searchview)
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun searchMovies(client: HttpClient, searchedMovie: String?) {
        runBlocking {
            val apiKey = "5272d12fcd7c9ef1b93f5ff8af93a411"
            val searchList: MutableList<Movie> = mutableListOf()

            for (page in 1..3) {
                val response =
                    client.get("https://api.themoviedb.org/3/search/movie?query=$searchedMovie&include_adult=false&language=en-US&page=$page&api_key=$apiKey")

                val jsonResponse = Gson().fromJson(response.bodyAsText(), MovieList::class.java)
                Log.d("TAGD", jsonResponse.results.toString())

                searchList.addAll(jsonResponse.results.mapNotNull{ Movie(it.id, it.poster_path, it.title, it.vote_average, it.release_date) })
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
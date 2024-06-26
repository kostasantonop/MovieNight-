package com.example.movienight.viewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.databinding.FragmentPopularBinding
import com.example.movienight.movie.Movie
import com.example.movienight.movie.MovieList
import com.example.movienight.viewpager.recycler.MovieAdapter
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var moviePopularAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popularList: MutableList<Movie> = mutableListOf()
        val client = HttpClient(CIO)
        val apiKey = "5272d12fcd7c9ef1b93f5ff8af93a411"

        runBlocking {
            for (page in 1..2){
                val response =
                    client.get("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=$page&sort_by=popularity.desc&api_key=$apiKey")

                val jsonResponse = Gson().fromJson(response.bodyAsText(), MovieList::class.java)

                popularList.addAll(jsonResponse.results.mapNotNull { Movie(it.id, it.poster_path, it.title, it.vote_average, it.release_date) })
            }
            moviePopularAdapter = MovieAdapter(requireContext(), popularList)
            binding.tab1RecyclerView.adapter = moviePopularAdapter
            binding.tab1RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}

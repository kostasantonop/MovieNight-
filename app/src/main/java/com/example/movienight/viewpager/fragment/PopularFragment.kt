package com.example.movienight.viewpager.fragment

import Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.MovieList
import com.example.movienight.databinding.FragmentTab1RecyclerViewBinding
import com.example.movienight.viewpager.adapter.MovieAdapter
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentTab1RecyclerViewBinding
    private lateinit var moviePopularAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab1RecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = HttpClient(CIO)

        runBlocking {
            //TODO(Get more than one pages)
            val response =
                client.get("https://api.themoviedb.org/3/movie/popular?api_key=5272d12fcd7c9ef1b93f5ff8af93a411")

            val jsonResponse = Gson().fromJson(response.bodyAsText(), MovieList::class.java)

            val popularList: List<Movie> = jsonResponse.results


            moviePopularAdapter = MovieAdapter(requireContext(), popularList)
            binding.tab1RecyclerView.adapter = moviePopularAdapter
            binding.tab1RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}

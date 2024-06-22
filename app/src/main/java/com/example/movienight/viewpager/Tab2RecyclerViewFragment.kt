package com.example.movienight.viewpager

import Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.MovieList
import com.example.movienight.databinding.FragmentTab2RecyclerViewBinding
import com.example.movienight.viewpager.tab1recyclerview.MovieAdapter
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking

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
    }
}
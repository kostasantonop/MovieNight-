package com.example.movienight

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movienight.movie.InfoMovie
import com.example.movienight.movie.Movie
import com.example.movienight.movie.MovieList
import com.example.movienight.viewpager.ViewPagerFragment
import com.example.movienight.viewpager.fragment.InfoFragment
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    private val _movies1: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies1: LiveData<List<Movie>> get() = _movies1

    private val _movies2: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies2: LiveData<List<Movie>> get() = _movies2

    private val _movies3: MutableLiveData<InfoMovie> = MutableLiveData()
    val movies3: LiveData<InfoMovie> get() = _movies3

    private val _streamFragments = MutableLiveData<Fragment>()
    val streamFragments: LiveData<Fragment> get() = _streamFragments

    fun itemSelected(data: String) {
        _streamFragments.postValue(InfoFragment.newInstance(data))
    }

    fun mainScreen() {
        _streamFragments.postValue(ViewPagerFragment.newInstance())
    }

    val apiKey = "5272d12fcd7c9ef1b93f5ff8af93a411"
    private val client = HttpClient(CIO)

    fun popularMovies() {
        viewModelScope.launch {
            val popularList: MutableList<Movie> = mutableListOf()
            for (page in 1..3) {
                val response = client.get("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=$page&sort_by=popularity.desc&api_key=$apiKey")
                val jsonResponse = Gson().fromJson(response.bodyAsText(), MovieList::class.java)
                popularList.addAll(jsonResponse.results.mapNotNull {
                    Movie(
                        it.id,
                        it.poster_path,
                        it.title,
                        it.vote_average,
                        it.release_date
                    )
                })
            }
            _movies1.postValue(popularList)
        }
    }

    fun searchMovies(searchedMovie: String) {
        viewModelScope.launch {
            val searchList: MutableList<Movie> = mutableListOf()
            for (page in 1..10) {
                val response = client.get("https://api.themoviedb.org/3/search/movie?query=$searchedMovie&include_adult=false&language=en-US&page=$page&api_key=$apiKey")
                val jsonResponse = Gson().fromJson(response.bodyAsText(), MovieList::class.java)
                searchList.addAll(jsonResponse.results.mapNotNull {
                    Movie(
                        it.id,
                        it.poster_path,
                        it.title,
                        it.vote_average,
                        it.release_date
                    )
                })
            }
            _movies2.postValue(searchList)
        }
    }

    fun infoMovies(movieId : String){
        viewModelScope.launch {
            val response = client.get("https://api.themoviedb.org/3/movie/$movieId?api_key=$apiKey")
            val jsonResponse = Gson().fromJson(response.bodyAsText(), InfoMovie::class.java)
            _movies3.postValue(jsonResponse)
        }
    }
}
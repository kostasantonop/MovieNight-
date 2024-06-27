package com.example.movienight.viewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movienight.MovieViewModel
import com.example.movienight.databinding.FragmentInfoBinding
import com.squareup.picasso.Picasso


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getString("selectedValue")
        if (movieId != null) {
            movieViewModel.infoMovies(movieId)
        }

        movieViewModel.movies3.observe(viewLifecycleOwner, Observer { infoMovieList ->
            infoMovieList?.let {
                binding.titleTextView.text = it.title
                binding.overviewTextView.text = it.overview
                binding.foreignTitleTextView.text = it.original_title
                binding.ratingTextView.text = String.format("%.1f", it.vote_average)
                binding.popularityTextView.text = "Popularity: ".plus(it.vote_count.toString())
                binding.dateTextView.text = it.release_date?.take(4)
                Picasso.get().load("https://image.tmdb.org/t/p/w500".plus(it.poster_path)).into(binding.imageView)
            }
        })

        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(value: String): InfoFragment {
            val fragment = InfoFragment()
            val parameters = Bundle()
            parameters.putString("selectedValue", value)
            fragment.arguments = parameters
            return fragment
        }
    }
}
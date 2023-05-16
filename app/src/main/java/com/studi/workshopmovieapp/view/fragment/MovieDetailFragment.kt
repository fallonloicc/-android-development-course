package com.studi.workshopmovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.studi.workshopmovieapp.R
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.view.activity.MainActivity
import com.studi.workshopmovieapp.viewmodel.MovieListViewmodel

class MovieDetailFragment: Fragment() {

    val args: MovieDetailFragmentArgs by navArgs()

    lateinit var viewModel: MovieListViewmodel

    lateinit var mainView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.mainView = view
        val background = view.findViewById<View>(R.id.movie_details_background)

        background.setBackgroundColor(
            (activity as MainActivity).getStatusBarColor()
        )

        val poster = view.findViewById<ImageView>(R.id.movie_details_poster)
        poster.apply {
            transitionName = args.uri
            Glide.with(context)
                .load(args.uri)
                .apply(RequestOptions.circleCropTransform())
                .into(this)
        }

        viewModel = MovieListViewmodel(app = requireActivity().application)
        initObserver()

//        viewModel.getMovieDetails(args.movieId)
    }

    private fun initObserver() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            updateData(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            displayError(it)
        }
    }

    private fun updateData(movie: Movie) {
        view?.let {
            val title = it.findViewById<TextView>(R.id.movie_details_title)
            val poster = it.findViewById<ImageView>(R.id.movie_details_poster)
            val descr = it.findViewById<TextView>(R.id.movie_details_descr)

            title.text = movie.title
            descr.text = movie.descr

            Glide.with(requireContext())
                .load(movie.posterUrl)
                .into(poster)
        }
    }

    private fun displayError(errorMessage: String){
        Toast.makeText(
            requireContext(),
            errorMessage,
            Toast.LENGTH_LONG
        ).show()
    }
}
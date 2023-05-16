package com.studi.workshopmovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.studi.workshopmovieapp.R
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.util.getRandomColor
import com.studi.workshopmovieapp.view.activity.MainActivity
import com.studi.workshopmovieapp.view.adapter.MovieSelectedListener
import com.studi.workshopmovieapp.view.adapter.MoviesAdapter
import com.studi.workshopmovieapp.viewmodel.MovieListViewmodel

class MovieListFragment: Fragment() {

    lateinit var viewModel: MovieListViewmodel
    lateinit var adapter: MoviesAdapter

    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = MovieListViewmodel(app = requireActivity().application)
        initObserver()
        viewModel.getMovieList()

        val recyclerView = view.findViewById<RecyclerView>(com.studi.workshopmovieapp.R.id.recycler_movie)
        adapter = MoviesAdapter(requireContext(), emptyList())
        adapter.movieSelectedListener = object : MovieSelectedListener {
            override fun onMovieSelected(movie: Movie, imageView: ImageView) {
                val extras = FragmentNavigatorExtras(
                    imageView to movie.posterUrl
                )

                val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetail(
                    movieId = movie.id, uri = movie.posterUrl
                )
                findNavController().navigate(action, extras)
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val toolbarColor = requireContext().getRandomColor()
        toolbar.setBackgroundColor(
            toolbarColor
        )
        (activity as MainActivity).setStatusBarColor(toolbarColor)

        refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.movie_refresh_layout)
        refreshLayout.setOnRefreshListener {
            viewModel.getMovieList()
        }
    }

    private fun initObserver(){
        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
            refreshLayout.isRefreshing = false
        }

        viewModel.error.observe(viewLifecycleOwner) {
            displayError(it)
            refreshLayout.isRefreshing = false
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
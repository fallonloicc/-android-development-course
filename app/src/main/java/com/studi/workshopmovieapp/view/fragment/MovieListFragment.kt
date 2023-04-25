package com.studi.workshopmovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studi.workshopmovieapp.R
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.util.getRandomColor
import com.studi.workshopmovieapp.view.activity.MainActivity
import com.studi.workshopmovieapp.view.adapter.MoviesAdapter

class MovieListFragment: Fragment() {

    private val movieList: List<Movie> = listOf(
        Movie("Bienvenue chez les chtis","Un film avec des Chtis"),
        Movie("20 milles lieux sous les mers","Un film avec des mers"),
        Movie("Prison Break", "Un prisonnier sympa")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(com.studi.workshopmovieapp.R.id.recycler_movie)
        val adapter = MoviesAdapter(requireContext(), movieList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val toolbarColor = requireContext().getRandomColor()
        toolbar.setBackgroundColor(
            toolbarColor
        )
        (activity as MainActivity).setStatusBarColor(toolbarColor)
    }
}
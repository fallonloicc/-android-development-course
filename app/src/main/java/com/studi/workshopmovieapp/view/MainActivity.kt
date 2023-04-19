package com.studi.workshopmovieapp.view

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.view.adapter.MoviesAdapter

class MainActivity : AppCompatActivity() {

    val movieList: List<Movie> = listOf(
        Movie("Bienvenue chez les chtis", R.drawable.ic_dialog_email),
        Movie("20 milles lieux sous les mers", R.drawable.ic_dialog_map),
        Movie("Prison Break", R.drawable.ic_lock_lock)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.studi.workshopmovieapp.R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(com.studi.workshopmovieapp.R.id.recycler_movie)
        val adapter = MoviesAdapter(this, getHugeMovieList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getHugeMovieList(): List<Movie>{
        var i = 0
        var list = mutableListOf<Movie>()
        while (i <= 100){
            list.add(
                Movie("Prison Break", R.drawable.ic_lock_lock)
            )
            i++
        }
        list.add(
            Movie("La fin", R.drawable.ic_lock_lock)
        )
        return list
    }
}
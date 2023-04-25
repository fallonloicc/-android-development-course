package com.studi.workshopmovieapp.view.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studi.workshopmovieapp.R
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.util.getRandomColor
import com.studi.workshopmovieapp.view.adapter.MoviesAdapter


class MainActivity : AppCompatActivity() {

    private val movieList = listOf<Movie>(
        Movie("Bienvenue chez les Chtis", "Bienvenue chez les Ch'tis est un film français réalisé par Dany Boon, sorti le 20 février 2008 dans le Nord-Pas-de-Calais et dans quelques salles de la Somme, le 27 février dans le reste de la France, en Belgique et en Suisse, un jour après au Luxembourg, et le 25 juillet au Canada."),
        Movie("Avatar", "Avatar est un film de science-fiction américain réalisé par James Cameron et sorti en 2009. Il s'agit du premier film de la franchise cinématographique Avatar.\n" +
                "\n" +
                "L’action se déroule en 21543 sur Pandora, une des lunes de Polyphème, une planète géante gazeuse en orbite autour d'Alpha Centauri A dans le système stellaire le plus proche de la Terre."),
        Movie("La vérité si je mens", "Terminator (The Terminator) est un film de science-fiction américain réalisé par James Cameron et sorti en 1984. Il met en scène dans les rôles principaux, Arnold Schwarzenegger, Michael Biehn et Linda Hamilton."),
        Movie("Titanic", "Titanic est un film dramatique américain écrit, produit et réalisé par James Cameron, sorti en 1997.\n" +
                "\n" +
                "Intégrant à la fois des aspects historiques et fictifs, le film est basé sur le récit du naufrage du RMS Titanic et met en vedette Leonardo DiCaprio et Kate Winslet."),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.studi.workshopmovieapp.R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT

        val recyclerView = findViewById<RecyclerView>(com.studi.workshopmovieapp.R.id.recycler_movie)
        val adapter = MoviesAdapter(this, movieList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarColor = this.getRandomColor()
        toolbar.setBackgroundColor(
            toolbarColor
        )
        setStatusBarColor(toolbarColor)
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun setStatusBarColor(color: Int) {
        window.statusBarColor = color
    }
}
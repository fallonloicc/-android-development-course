package com.studi.workshopmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // "https://guides.codepath.com/android/using-the-recyclerview"


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_film)
        val filmList: List<Film> = listOf(
            Film("Le grand bleu", android.R.drawable.ic_dialog_alert),
            Film("Le moyen bleu", android.R.drawable.ic_lock_idle_alarm),
            Film("Le petit bleu", android.R.drawable.ic_dialog_info),
        )
        val adapter = FilmsAdapter(this, filmList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
package com.studi.workshopmovieapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView


class FilmsAdapter (private val context: Context, private val filmList: List<Film>) : RecyclerView.Adapter<FilmsAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById<ImageView>(com.studi.workshopmovieapp.R.id.imageview_film)
        val textView: TextView = itemView.findViewById<TextView>(com.studi.workshopmovieapp.R.id.tv_film_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(com.studi.workshopmovieapp.R.layout.item_film, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: FilmsAdapter.ViewHolder, position: Int) {
        val film: Film = filmList[position]
        val textView = viewHolder.textView
        textView.text = film.title
        val image = viewHolder.imageView
        image.setImageDrawable(
            AppCompatResources.getDrawable(context, film.icon_id)
        )
    }

    override fun getItemCount(): Int {
        return filmList.size
    }
}
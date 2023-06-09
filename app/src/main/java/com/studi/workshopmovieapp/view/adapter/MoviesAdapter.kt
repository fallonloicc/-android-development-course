package com.studi.workshopmovieapp.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.studi.workshopmovieapp.R
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.util.getColorByOrder
import com.studi.workshopmovieapp.view.fragment.MovieListFragmentDirections


class MoviesAdapter(private val context: Context, movieList: List<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var list = movieList

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.item_poster)
        val textView: TextView = itemView.findViewById(R.id.item_title)
        val arrow: ImageView = itemView.findViewById(R.id.navigate_arrow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(movieView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]
        holder.textView.text = movie.title

        Glide.with(context)
            .load(movie.posterUrl)
            .into(holder.imageView)

        holder.arrow.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetail(
                movieId = movie.id
            )
            it.findNavController().navigate(action)
        }
    }

    fun updateData(movieList: List<Movie>){
        list = movieList
        notifyDataSetChanged()
    }
}

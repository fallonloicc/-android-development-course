package com.studi.workshopmovieapp.view.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.studi.workshopmovieapp.R
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.util.getColorByOrder
import com.studi.workshopmovieapp.view.fragment.MovieListFragmentDirections

class MoviesAdapter(private val context: Context, private val movieList: List<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.item_poster)
        val textView: TextView = itemView.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(movieView)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.textView.text = movie.title

        val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.item_rounded_icon, null)
        drawable?.colorFilter = PorterDuffColorFilter(context.getColorByOrder(position), PorterDuff.Mode.MULTIPLY)
        holder.imageView.background = drawable

        holder.itemView.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetail(
                movieTitle = movie.title,
                movieDescr = movie.descr
            )
            it.findNavController().navigate(action)
        }
    }
}

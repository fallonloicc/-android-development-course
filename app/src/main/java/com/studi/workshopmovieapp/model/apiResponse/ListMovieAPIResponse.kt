package com.studi.workshopmovieapp.model.apiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.studi.workshopmovieapp.model.Movie

class ListMovieAPIResponse {

    @SerializedName("results")
    @Expose
    var results: List<MovieAPIResponse>? = null

    fun toMovieList(): List<Movie> {
        var movieList = mutableListOf<Movie>()
        results?.forEach {
            movieList.add(
                it.toMovie()
            )
        }

        return movieList
    }
}
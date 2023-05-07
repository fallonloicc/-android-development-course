package com.studi.workshopmovieapp.model.apiResponse

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.repository.MovieRepository.Companion.URLIMAGE


class MovieAPIResponse {

    @SerializedName("id")
    @Expose
    private val id: String? = null

    @SerializedName("title")
    @Expose
    private val title: String? = null

    @SerializedName("poster_path")
    @Expose
    private val imagePath: String? = null

    @SerializedName("release_date")
    @Expose
    private val releaseDate: String? = null

    @SerializedName("overview")
    @Expose
    private val overview: String? = null

    @SerializedName("vote_average")
    @Expose
    private val voteAverage: String? = null

    fun toMovie(): Movie {
        var url = imagePath?.let {
            URLIMAGE + it.replace("/", "")
//            + "?api_key=" + TMDBapiKey
        } ?: ""

        return Movie(
            id = id ?: "",
            title = title ?: "",
            descr = overview ?: "",
            posterUrl = url
        )
    }
}
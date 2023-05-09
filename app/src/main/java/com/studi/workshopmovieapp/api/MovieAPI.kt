package com.studi.workshopmovieapp.api

import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.model.apiResponse.ListMovieAPIResponse
import com.studi.workshopmovieapp.model.apiResponse.MovieAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    companion object {
        const val TMDBapiKey: String = "3f2f98bf7eebf97cac92d91720679bbf"
    }

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String = TMDBapiKey
    ): Response<ListMovieAPIResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = TMDBapiKey
    ): Response<MovieAPIResponse>

}
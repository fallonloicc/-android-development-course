package com.studi.workshopmovieapp.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.studi.workshopmovieapp.api.MovieAPI
import com.studi.workshopmovieapp.model.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MovieRepository {

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val movieAPI :  MovieAPI by lazy{
        retrofit.create(MovieAPI::class.java)
    }

    suspend fun getMovieList(): MovieAPIResult {
        return try {
            val result = movieAPI.getMovieList()
            if (result.isSuccessful){

                val response = result.body() ?: return EmptyListAPIResult
                response.results?.let {
                    if (it.isEmpty()){
                        EmptyListAPIResult
                    }
                    else {
                        SuccessMovieAPIResult(data = response.toMovieList())
                    }
                } ?: EmptyListAPIResult
            }
            else {
                ErrorMovieAPIResult(
                    code = result.code(),
                    message = result.message()
                )
            }
        }
        catch (e: Exception){
            Timber.e(e)
            ErrorMovieAPIResult(code = 999, message = "")
        }
    }

    suspend fun getMovieDetails(movieId: String): MovieAPIResult {
        return try {
            val result = movieAPI.getMovieDetails(movieId = movieId)
            if (result.isSuccessful){
                result.body()?.let {
                    SuccessMovieDetailsResult(
                        data = it.toMovie()
                    )
                } ?: ErrorMovieAPIResult(code = 998, message = "Body null")

            }
            else {
                ErrorMovieAPIResult(
                    code = result.code(),
                    message = result.message()
                )
            }
        } catch (e: Exception) {
            Timber.e(e)
            ErrorMovieAPIResult(
                code = 999,
                message = e.message ?: ""
            )
        }
    }

    companion object {
        const val BASE_URL: String = "https://api.themoviedb.org/3/"
        const val URLIMAGE: String = "https://image.tmdb.org/t/p/w780/"
    }
}

sealed class MovieAPIResult
data class SuccessMovieAPIResult(val data: List<Movie>): MovieAPIResult()
data class SuccessMovieDetailsResult(val data: Movie): MovieAPIResult()
object EmptyListAPIResult: MovieAPIResult()
data class ErrorMovieAPIResult(val code: Int, val message: String): MovieAPIResult()
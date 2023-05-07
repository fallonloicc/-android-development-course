package com.studi.workshopmovieapp.database.repository

import com.studi.workshopmovieapp.database.dao.MovieDao
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.model.dao.MovieEntity
import com.studi.workshopmovieapp.model.dao.MovieEntity.Companion.toEntity
import com.studi.workshopmovieapp.model.dao.MovieEntity.Companion.toModel
import timber.log.Timber
import java.lang.Exception

class MovieDbRepository(private val movieDao: MovieDao) {

    fun getAllMovie(): List<Movie>? {
        return try {
            val movieDbList = movieDao.getAllMovie()

            return mutableListOf<Movie>().apply {
                movieDbList.forEach {
                    this.add(
                        it.toModel()
                    )
                }
            }
        }catch (e: Exception) {
            Timber.e(e)
            null
        }

    }

    suspend fun insertMovie(movie: Movie){
        try {
            movieDao.insertMovie(
                movie.toEntity()
            )
        } catch (e: Exception) {
            Timber.e(e)
        }

    }

    suspend fun insertMovieList(movieList: List<Movie>){
        movieList.forEach {
            insertMovie(it)
        }
    }
}
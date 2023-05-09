package com.studi.workshopmovieapp.database.repository

import com.studi.workshopmovieapp.database.dao.MovieDao
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.model.dao.MovieEntity
import com.studi.workshopmovieapp.model.dao.MovieEntity.Companion.toEntity
import com.studi.workshopmovieapp.model.dao.MovieEntity.Companion.toModel
import timber.log.Timber
import java.lang.Exception

class MovieDbRepository(private val movieDao: MovieDao) {

    fun getAllMovie(): DbResponse {
        return try {
            val movieDbList = movieDao.getAllMovie()

            val movieList = mutableListOf<Movie>().apply {
                movieDbList.forEach {
                    this.add(
                        it.toModel()
                    )
                }
            }

            DbSuccessResponse(
                data = movieList
            )
        }catch (e: Exception) {
            Timber.e(e)
            DbErrorResponse(
                exception = e
            )
        }

    }

    private suspend fun insertMovie(movie: Movie){
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

sealed class DbResponse()
data class DbSuccessResponse(val data: List<Movie>): DbResponse()
data class DbErrorResponse(val exception: Exception): DbResponse()
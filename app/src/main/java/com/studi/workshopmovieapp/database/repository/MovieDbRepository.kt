package com.studi.workshopmovieapp.database.repository

import com.studi.workshopmovieapp.database.dao.MovieDao
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.model.dao.MovieEntity
import com.studi.workshopmovieapp.model.dao.MovieEntity.Companion.toEntity
import com.studi.workshopmovieapp.model.dao.MovieEntity.Companion.toModel

class MovieDbRepository(private val movieDao: MovieDao) {

    suspend fun getAllMovie(): List<Movie>{
        val movieDbList = movieDao.getAllMovie()

        return mutableListOf<Movie>().apply {
            movieDbList.forEach {
                this.add(
                    it.toModel()
                )
            }
        }
    }

    suspend fun insertMovie(movie: Movie){
        movieDao.insertMovie(
            movie.toEntity()
        )
    }

    suspend fun insertMovieList(movieList: List<Movie>){
        movieList.forEach {
            insertMovie(it)
        }
    }
}
package com.studi.workshopmovieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studi.workshopmovieapp.database.MovieDb
import com.studi.workshopmovieapp.database.repository.DbErrorResponse
import com.studi.workshopmovieapp.database.repository.DbSuccessResponse
import com.studi.workshopmovieapp.database.repository.MovieDbRepository
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.repository.EmptyListAPIResult
import com.studi.workshopmovieapp.repository.ErrorMovieAPIResult
import com.studi.workshopmovieapp.repository.MovieAPIResult
import com.studi.workshopmovieapp.repository.MovieRepository
import com.studi.workshopmovieapp.repository.SuccessMovieAPIResult
import com.studi.workshopmovieapp.util.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewmodel(
    val app: Application
): AndroidViewModel(app) {

    private var repository: MovieRepository = MovieRepository()

    private val movieDb by lazy { MovieDb.getDatabase(app) }
    private val dbRepository by lazy { MovieDbRepository(movieDb.movieDao()) }

    private var _error = MutableLiveData<String>()
    var error: LiveData<String> = _error

    private var _movieList = MutableLiveData<List<Movie>>()
    var movieList: LiveData<List<Movie>> = _movieList

    init {
        getMovieList()
    }

    fun getMovieList(){
        viewModelScope.launch {
            if (app.isOnline()) {
                val apiResult: MovieAPIResult = repository.getMovieList()
                when (apiResult) {
                    is SuccessMovieAPIResult -> {
                        apiResult.data.apply {
                            dbRepository.insertMovieList(this)
                            _movieList.postValue(this)
                        }
                    }

                    is EmptyListAPIResult -> {
                        _error.postValue(
                            "La liste est vide"
                        )
                    }

                    is ErrorMovieAPIResult -> {
                        handleAPIError(apiResult)
                    }
                }
            } else {
                _error.postValue(
                    "Pas de connexion Internet"
                )
                getDbMovies()
            }
        }
    }

    private fun getDbMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            when(val movieList = dbRepository.getAllMovie()) {
                is DbSuccessResponse -> {
                    _movieList.postValue(
                        movieList.data
                    )
                }
                is DbErrorResponse -> {
                    _error.postValue(
                        movieList.exception.message ?: ""
                    )
                }
            }
        }
    }

    private fun handleAPIError(error: ErrorMovieAPIResult){
        val errorMessage = when(error.code){
            401 -> "Unauthorized"
            else -> error.message
        }

        _error.postValue(errorMessage)
    }

}
package com.studi.workshopmovieapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studi.workshopmovieapp.model.Movie
import com.studi.workshopmovieapp.repository.*
import com.studi.workshopmovieapp.util.isOnline
import kotlinx.coroutines.launch

class MovieListViewmodel(
    val app: Application
): AndroidViewModel(app) {

    var repository: MovieRepository = MovieRepository()

    private var _error = MutableLiveData<String>()
    var error: LiveData<String> = _error

    private var _movieList = MutableLiveData<List<Movie>>()
    var movieList: LiveData<List<Movie>> = _movieList

    init {
        getMovieList()
    }

    private fun getMovieList(){
        if (app.isOnline()){
            viewModelScope.launch {
                val apiResult: MovieAPIResult = repository.getMovieList()
                when(apiResult){
                    is SuccessMovieAPIResult -> {
                        _movieList.postValue(apiResult.data)
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
            }
        }
        else {
            _error.postValue(
                "Pas de connexion Internet"
            )
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
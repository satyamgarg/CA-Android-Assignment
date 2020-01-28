package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.backbase.assignment.data.MovieBoxRepository

class MovieBoxViewModel(private val repository: MovieBoxRepository) : ViewModel() {

    fun getMovies() = repository.getMovies()
    fun getPopularMovies() = repository.getPopularMovies()
    fun getMovieDetails(movieId:String) = repository.getMovieDetails(movieId)

}

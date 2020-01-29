package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.backbase.assignment.data.MovieRepository

class MovieBoxViewModel(private val repository: MovieRepository) : ViewModel() {
    fun getMovies() = repository.getMovies()
    fun getPopularMovies() = repository.getPopularMovies()
}

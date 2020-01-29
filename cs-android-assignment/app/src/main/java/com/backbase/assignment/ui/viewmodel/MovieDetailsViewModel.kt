package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.backbase.assignment.data.MovieRepository

class MovieDetailsViewModel (private val repository: MovieRepository): ViewModel() {
    fun getMovieDetails(movieId:String) = repository.getMovieDetails(movieId)
}

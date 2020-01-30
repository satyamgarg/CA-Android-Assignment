package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.backbase.assignment.data.MovieRepository

class MovieDetailsViewModel (private val repository: MovieRepository): ViewModel() {
    /**
     * Get movie details using movie id
     * @param movieId - Movie Id
     * @return - Movie details
     */
    fun getMovieDetails(movieId:String) = repository.getMovieDetails(movieId)
}

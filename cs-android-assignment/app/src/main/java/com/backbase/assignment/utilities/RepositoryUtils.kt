package com.backbase.assignment.utilities

import com.backbase.assignment.data.MovieRepository
import com.backbase.assignment.ui.viewmodel.MovieDetailsViewModelFactory
import com.backbase.assignment.ui.viewmodel.MovieViewModelFactory

object RepositoryUtils {

    /**
     * Factory method to provide instance of MovieViewModelFactory
     * @return - MovieViewModelFactory
     */
    fun provideMovieBoxViewModelFactory(): MovieViewModelFactory {
        val movieRepository = MovieRepository.getInstance()
        return MovieViewModelFactory(movieRepository)
    }

    /**
     * Factory method to provide instance of MovieDetailsViewModelFactory
     * @return - MovieDetailsViewModelFactory
     */
    fun provideMovieDetailsViewModelFactory(): MovieDetailsViewModelFactory {
        val movieRepository = MovieRepository.getInstance()
        return MovieDetailsViewModelFactory(movieRepository)
    }
}
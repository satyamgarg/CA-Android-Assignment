package com.backbase.assignment.ui.fragment

import com.backbase.assignment.data.MovieRepository
import com.backbase.assignment.ui.viewmodel.MovieDetailsViewModelFactory
import com.backbase.assignment.ui.viewmodel.MovieViewModelFactory

object FactoryProvider {

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
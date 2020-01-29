package com.backbase.assignment.utilities

import com.backbase.assignment.data.MovieRepository
import com.backbase.assignment.ui.viewmodel.MovieDetailsViewModelFactory
import com.backbase.assignment.ui.viewmodel.MovieViewModelFactory

object RepositoryUtils {

    fun provideMovieBoxViewModelFactory(): MovieViewModelFactory {
        val movieRepository = MovieRepository.getInstance()
        return MovieViewModelFactory(movieRepository)
    }

    fun provideMovieDetailsViewModelFactory(): MovieDetailsViewModelFactory {
        val movieRepository = MovieRepository.getInstance()
        return MovieDetailsViewModelFactory(movieRepository)
    }
}
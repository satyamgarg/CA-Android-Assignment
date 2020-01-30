package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.assignment.data.MovieRepository

class MovieDetailsViewModelFactory(private val movieBoxRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    /**
     *  Create instance of MovieDetailsViewModel
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(movieBoxRepository) as T
    }
}
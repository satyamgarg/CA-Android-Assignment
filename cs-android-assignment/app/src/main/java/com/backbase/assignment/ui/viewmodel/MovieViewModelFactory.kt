package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.assignment.data.MovieRepository

class MovieViewModelFactory(private val movieBoxRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    /**
     * Create instance of MovieBoxViewModel
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieBoxViewModel(movieBoxRepository) as T
    }
}
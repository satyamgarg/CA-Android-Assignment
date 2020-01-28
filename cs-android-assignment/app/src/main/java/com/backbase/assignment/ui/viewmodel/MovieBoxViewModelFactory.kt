package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.assignment.data.MovieBoxRepository

class MovieBoxViewModelFactory(private val movieBoxRepository: MovieBoxRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieBoxViewModel(movieBoxRepository) as T
    }
}
package com.company.mvvm.utilities

import com.backbase.assignment.data.MovieBoxRepository
import com.backbase.assignment.ui.viewmodel.MovieBoxViewModelFactory

object RepositoryUtils {

    fun provideBooksViewModelFactory(): MovieBoxViewModelFactory{
        val bookRepository = MovieBoxRepository.getInstance()
        return MovieBoxViewModelFactory(bookRepository)
    }
}
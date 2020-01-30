package com.backbase.assignment.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

import com.backbase.assignment.model.Movie

class MovieDataSourceFactory(
    private val movieDataService: MoviesDataService
) : DataSource.Factory<Long, Movie?>() {
    private val mutableLiveData: MutableLiveData<MovieDataSource> = MutableLiveData()

    /**
     * Create MovieDataSource
     */
    override fun create(): MovieDataSource {
        val movieDataSource = MovieDataSource()
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }

    /**
     * Get movie data source for popular movies
     */
    fun getMutableLiveData(): MutableLiveData<MovieDataSource> {
        return mutableLiveData
    }
}
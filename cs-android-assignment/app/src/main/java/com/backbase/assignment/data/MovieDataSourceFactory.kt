package com.backbase.assignment.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.backbase.assignment.model.Movie

class MovieDataSourceFactory() : DataSource.Factory<Long, Movie?>() {
    private val mutableLiveData: MutableLiveData<MovieDataSource> = MutableLiveData()

    /**
     * Create MovieDataSource
     */
    override fun create(): MovieDataSource {
        val movieDataSource = MovieDataSource()
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }

    fun getMutableLiveData(): MutableLiveData<MovieDataSource> {
        return mutableLiveData
    }

}
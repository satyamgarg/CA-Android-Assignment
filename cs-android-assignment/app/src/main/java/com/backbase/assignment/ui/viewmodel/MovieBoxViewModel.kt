package com.backbase.assignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.backbase.assignment.data.MovieRepository
import com.backbase.assignment.data.model.MoviePlayingNowResponse

class MovieBoxViewModel(private val repository: MovieRepository) : ViewModel() {
    fun getMovies() = repository.getMovies()
    fun getPopularMovies() = repository.getPopularMovies()


    /*var postsLiveData  : LiveData<PagedList<MoviePlayingNowResponse>>
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }
    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, MoviePlayingNowResponse> {

        val dataSourceFactory = object : DataSource.Factory<String, MoviePlayingNowResponse>() {
            override fun create(): DataSource<String, MoviePlayingNowResponse> {
                return MovieRepository(viewModelScope)
            }
        }
        return LivePagedListBuilder<String, MoviePlayingNowResponse>(dataSourceFactory, config)
    }*/
}

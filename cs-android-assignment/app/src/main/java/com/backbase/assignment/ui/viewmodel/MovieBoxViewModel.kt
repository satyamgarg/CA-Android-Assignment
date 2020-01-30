package com.backbase.assignment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.backbase.assignment.data.ApiClient
import com.backbase.assignment.data.MovieDataSourceFactory
import com.backbase.assignment.data.MovieRepository
import com.backbase.assignment.model.Movie
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MovieBoxViewModel(private val repository: MovieRepository) : ViewModel() {

    var executor: Executor
    var moviesPageList: LiveData<PagedList<Movie>>

    init {
        val movieDataService = ApiClient.getService()
        val movieDataSourceFactory = MovieDataSourceFactory(movieDataService)
        var movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData()
        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(15)
            .setPrefetchDistance(4)
            .build()
        executor = Executors.newFixedThreadPool(5)
        moviesPageList = LivePagedListBuilder<Long, Movie>(movieDataSourceFactory, config)
            .setFetchExecutor(executor)
            .build()
    }

    /**
     * Get playing now movie list
     * @return List of movies
     */
    fun getPlayingNowMovies() = repository.getPlayingNowMovies()

    /**
     * Get Popular movie list
     * @return List of movies
     */
    fun getPopularMovies(): LiveData<PagedList<Movie>> = moviesPageList

}

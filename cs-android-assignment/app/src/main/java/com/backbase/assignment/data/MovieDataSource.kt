package com.backbase.assignment.data

import androidx.paging.PageKeyedDataSource
import com.backbase.assignment.data.ApiClient.getService
import com.backbase.assignment.data.NetworkApiConfig.apiKey
import com.backbase.assignment.data.model.PopularMovieResponse
import com.backbase.assignment.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieDataSource() : PageKeyedDataSource<Long?, Movie?>() {

    private var popularMovieResponse = PopularMovieResponse()

    override fun loadInitial(
        params: LoadInitialParams<Long?>,
        callback: LoadInitialCallback<Long?, Movie?>
    ) {
        val call =
            getService().getPopularMoviesWithPagging(apiKey, 1)
        call.enqueue(object : Callback<PopularMovieResponse?> {
            override fun onResponse(
                call: Call<PopularMovieResponse?>,
                response: Response<PopularMovieResponse?>
            ) {
                var moviesList: List<Movie> = ArrayList()
                if (response.code() == 200) {
                    popularMovieResponse = response.body() ?: popularMovieResponse
                    moviesList = popularMovieResponse.results ?: moviesList
                    if (moviesList.isNotEmpty()) {
                        callback.onResult(moviesList, null, 2)
                    }
                }
            }

            override fun onFailure(
                call: Call<PopularMovieResponse?>,
                t: Throwable
            ) {
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Long?>,
        callback: LoadCallback<Long?, Movie?>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Long?>,
        callback: LoadCallback<Long?, Movie?>
    ) {

        val call =
            params.key?.let { getService().getPopularMoviesWithPagging(apiKey, it) }
        call?.enqueue(object : Callback<PopularMovieResponse?> {
            override fun onResponse(
                call: Call<PopularMovieResponse?>,
                response: Response<PopularMovieResponse?>
            ) {
                var moviesList: List<Movie> =
                    ArrayList<Movie>()
                if (response.code() == 200) {
                    popularMovieResponse = response.body() ?: popularMovieResponse
                    moviesList = popularMovieResponse.results ?: moviesList

                    if (moviesList.isNotEmpty()) {
                        callback.onResult(moviesList, params.key?.plus(1))
                    }
                }
            }

            override fun onFailure(
                call: Call<PopularMovieResponse?>,
                t: Throwable
            ) {
            }
        })
    }

}
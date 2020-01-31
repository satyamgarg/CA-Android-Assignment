package com.backbase.assignment.data

import androidx.annotation.NonNull
import androidx.paging.PageKeyedDataSource
import com.backbase.assignment.model.PopularMovieResponse
import com.backbase.assignment.model.Movie
import com.backbase.assignment.service.NetworkApiConfig.apiKey
import com.backbase.assignment.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDataSource : PageKeyedDataSource<Long?, Movie?>() {

    private var popularMovieResponse =
        PopularMovieResponse()
    override fun loadInitial(
        @NonNull params: LoadInitialParams<Long?>,
        @NonNull callback: LoadInitialCallback<Long?, Movie?>
    ) {
        val call =
            RetrofitInstance.service.getPopularMoviesWithPaging(apiKey, 1)
        call.enqueue(object : Callback<PopularMovieResponse?> {
            override fun onResponse(
                call: Call<PopularMovieResponse?>,
                response: Response<PopularMovieResponse?>
            ) {
                val moviesList: ArrayList<Movie>
                if (response.code() == 200) {
                    popularMovieResponse = response.body() ?: popularMovieResponse
                    moviesList = popularMovieResponse.results as ArrayList<Movie>
                    if (moviesList.isNotEmpty()) {
                        callback.onResult(moviesList as List<Movie?>, null, 2)
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
        @NonNull  params: LoadParams<Long?>,
        @NonNull  callback: LoadCallback<Long?, Movie?>
    ) {
    }

    override fun loadAfter(
        @NonNull params: LoadParams<Long?>,
        @NonNull callback: LoadCallback<Long?, Movie?>
    ) {

            val call =
                params.key?.let { RetrofitInstance.service.getPopularMoviesWithPaging(apiKey, it) }
            call?.enqueue(object : Callback<PopularMovieResponse?> {
                override fun onResponse(
                    call: Call<PopularMovieResponse?>,
                    response: Response<PopularMovieResponse?>
                ) {
                    val moviesList: ArrayList<Movie>
                    if (response.code() == 200) {
                        popularMovieResponse = response.body() ?: popularMovieResponse
                        moviesList = popularMovieResponse.results as ArrayList<Movie>

                        if (moviesList.isNotEmpty()) {
                            callback.onResult(moviesList as List<Movie?>, params.key!! + 1)
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
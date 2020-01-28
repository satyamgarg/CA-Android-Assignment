package com.backbase.assignment.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.backbase.assignment.data.model.MovieDetailResponse
import com.backbase.assignment.data.model.MovieResponse
import com.backbase.assignment.data.model.PopularMovieResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieBoxRepository {

    private var movieResponse = MovieResponse()
    private val movieResponseLiveData = MutableLiveData<MovieResponse>()

    private var popularMovieResponse = PopularMovieResponse()
    private val popularMovieResponseLiveData = MutableLiveData<PopularMovieResponse>()

    private var movieDetailResponse = MovieDetailResponse()
    private val movieDetailResponseLiveData = MutableLiveData<MovieDetailResponse>()


    companion object {
        @Volatile
        private var instance: MovieBoxRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: MovieBoxRepository().also { instance = it }
            }
    }

    fun getMovies(): MutableLiveData<MovieResponse> {
        val request = MoviesNetworkApi.create().getMovies(
            NetworkApiConfig.language,
            NetworkApiConfig.page, NetworkApiConfig.apiKey
        )

        request.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.code() == 200) {
                    Log.d(
                        "MovieBoxRepository",
                        "Success Response:-${Gson().toJson(response.body())}"
                    )
                    movieResponse = response.body() ?: MovieResponse()
                    movieResponseLiveData.value = movieResponse
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("MovieBoxRepository", "Failure Response:-${t.printStackTrace()}")
            }
        })

        return movieResponseLiveData
    }

    fun getPopularMovies(): MutableLiveData<PopularMovieResponse> {
        val request = MoviesNetworkApi.create().getPopularMovies(
            NetworkApiConfig.apiKey
        )

        request.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.code() == 200) {
                    val res = Gson().toJson(response.body())
                    Log.d(
                        "MovieBoxRepository",
                        "Popular movie Success Response:-${Gson().toJson(response.body())}"
                    )
                    popularMovieResponse = response.body() ?: PopularMovieResponse()
                    popularMovieResponseLiveData.value = popularMovieResponse
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.d("MovieBoxRepository", "Failure Response:-${t.printStackTrace()}")
            }
        })

        return popularMovieResponseLiveData
    }

    fun getMovieDetails(movieId: String): MutableLiveData<MovieDetailResponse> {
        val request = MoviesNetworkApi.create().getMovieDetails(
            movieId, NetworkApiConfig.apiKey
        )

        request.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.code() == 200) {
                    val res = Gson().toJson(response.body())
                    Log.d(
                        "MovieBoxRepository",
                        "Success Response:-${Gson().toJson(response.body())}"
                    )
                    movieDetailResponse = response.body() ?: MovieDetailResponse()
                    movieDetailResponseLiveData.value = movieDetailResponse
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.d("MovieBoxRepository", "Failure Response:-${t.printStackTrace()}")
            }
        })

        return movieDetailResponseLiveData
    }


}
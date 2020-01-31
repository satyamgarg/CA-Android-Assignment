package com.backbase.assignment.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.backbase.assignment.data.model.MovieDetailResponse
import com.backbase.assignment.data.model.MoviePlayingNowResponse
import com.backbase.assignment.service.NetworkApiConfig
import com.backbase.assignment.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val TAG = MovieRepository::class.java.simpleName
    private var movieResponse = MoviePlayingNowResponse()
    private val movieResponseLiveData = MutableLiveData<MoviePlayingNowResponse>()

    private var movieDetailResponse = MovieDetailResponse()
    private val movieDetailResponseLiveData = MutableLiveData<MovieDetailResponse>()


    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: MovieRepository().also { instance = it }
            }
    }

    /**
     * Get playing movies
     * @return MutableLiveData<MoviePlayingNowResponse>
     */
    fun getPlayingNowMovies(): MutableLiveData<MoviePlayingNowResponse> {
        val request = RetrofitInstance.service.getMoviePlayingNow(
            NetworkApiConfig.language,
            NetworkApiConfig.page, NetworkApiConfig.apiKey
        )

        request.enqueue(object : Callback<MoviePlayingNowResponse> {
            override fun onResponse(
                call: Call<MoviePlayingNowResponse>,
                response: Response<MoviePlayingNowResponse>
            ) {
                if (response.code() == 200) {
                    movieResponse = response.body() ?: movieResponse
                    movieResponseLiveData.value = movieResponse
                }
            }

            override fun onFailure(call: Call<MoviePlayingNowResponse>, t: Throwable) {
                Log.d(TAG, "${t.printStackTrace()}")
            }
        })

        return movieResponseLiveData
    }


    /**
     * Get movie details
     * @param movieId - Movie ID
     * @return MutableLiveData<MovieDetailResponse>
     */
    fun getMovieDetails(movieId: String): MutableLiveData<MovieDetailResponse> {
        val request = RetrofitInstance.service.getMovieDetails(
            movieId, NetworkApiConfig.apiKey
        )

        request.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.code() == 200) {
                    movieDetailResponse = response.body() ?: movieDetailResponse
                    movieDetailResponseLiveData.value = movieDetailResponse
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.d(TAG, "${t.printStackTrace()}")
            }
        })

        return movieDetailResponseLiveData
    }


}
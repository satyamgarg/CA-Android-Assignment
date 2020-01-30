package com.backbase.assignment.data

import com.backbase.assignment.data.model.MovieDetailResponse
import com.backbase.assignment.data.model.MoviePlayingNowResponse
import com.backbase.assignment.data.model.PopularMovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDataService {

    @GET("movie/now_playing?")
    fun getMovies(@Query("language") language: String, @Query("page") page: String, @Query("api_key") api_key: String): Call<MoviePlayingNowResponse>

    @GET("movie/{movie_id}?")
    fun getMovieDetails(@Path("movie_id") movie_id: String, @Query("api_key") api_key: String): Call<MovieDetailResponse>

    @GET("movie/popular?")
    fun getPopularMoviesWithPagging(@Query("api_key") api_key: String, @Query("page") page: Long): Call<PopularMovieResponse>


}
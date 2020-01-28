package com.backbase.assignment.data.model

import com.backbase.assignment.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PopularMovieResponse {
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var total_results: Int? = null
    @SerializedName("total_pages")
    @Expose
    var total_pages: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null
}
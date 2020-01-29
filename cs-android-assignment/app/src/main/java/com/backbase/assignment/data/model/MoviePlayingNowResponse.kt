package com.backbase.assignment.data.model

import com.backbase.assignment.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviePlayingNowResponse {
    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("dates")
    @Expose
    var dates: Dates? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
}
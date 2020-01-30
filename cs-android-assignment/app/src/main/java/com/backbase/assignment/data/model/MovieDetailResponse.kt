package com.backbase.assignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetailResponse {
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = false
    @SerializedName("backdrop_path")
    @Expose
    var backdrop_path: String? = null
    @SerializedName("belongs_to_collection")
    @Expose
    var belongs_to_collection: BelongsToCollection? = null
    @SerializedName("budget")
    @Expose
    var budget: Long? = null
    @SerializedName("genres")
    @Expose
    var genres: List<Genres>? = null
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @SerializedName("id")
    @Expose
    private var id: Long? = null
    @SerializedName("imdb_id")
    @Expose
    var imdb_id: String? = null
    @SerializedName("original_language")
    @Expose
    var original_language: String? = null
    @SerializedName("original_title")
    @Expose
    var original_title: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
    var poster_path: String? = null
    @SerializedName("production_companies")
    @Expose
    var production_companies: List<ProductionCompanies>? = null
    @SerializedName("production_countries")
    @Expose
    var production_countries: List<ProductionCountries>? = null
    @SerializedName("release_date")
    @Expose
    var release_date: String? = null
    @SerializedName("revenue")
    @Expose
    var revenue: Double? = null
    @SerializedName("runtime")
    @Expose
    var runtime = 0f
    @SerializedName("spoken_languages")
    @Expose
    var spoken_languages: List<SpokenLanguage>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("video")
    @Expose
    var video: Boolean? = false
    @SerializedName("vote_average")
    @Expose
    var vote_average: Double? = null
    @SerializedName("vote_count")
    @Expose
    var vote_count: Long? = null
}
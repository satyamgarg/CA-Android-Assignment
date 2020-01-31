package com.backbase.assignment.data.model

import android.os.Parcel
import android.os.Parcelable
import com.backbase.assignment.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PopularMovieResponse() : Parcelable{
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

    constructor(parcel: Parcel) : this() {
        page = parcel.readValue(Int::class.java.classLoader) as? Int
        total_results = parcel.readValue(Int::class.java.classLoader) as? Int
        total_pages = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(page)
        parcel.writeValue(total_results)
        parcel.writeValue(total_pages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularMovieResponse> {
        override fun createFromParcel(parcel: Parcel): PopularMovieResponse {
            return PopularMovieResponse(parcel)
        }

        override fun newArray(size: Int): Array<PopularMovieResponse?> {
            return arrayOfNulls(size)
        }
    }
}
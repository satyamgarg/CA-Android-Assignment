package com.backbase.assignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Belongs_to_collection {
    @SerializedName("id")
    @Expose
    private val id :Int ?= null
    @SerializedName("name")
    @Expose
    private val name: String? = null
    @SerializedName("poster_path")
    @Expose
    private val poster_path: String? = null
    @SerializedName("backdrop_path")
    @Expose
    private val backdrop_path: String? = null

}
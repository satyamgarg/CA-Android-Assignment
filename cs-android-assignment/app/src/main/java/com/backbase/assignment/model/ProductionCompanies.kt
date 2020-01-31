package com.backbase.assignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCompanies {
    @SerializedName("id")
    @Expose
    private val id:Int ?= null
    @SerializedName("logo_path")
    @Expose
    private val logo_path: String? = null
    @SerializedName("backdrop_path")
    @Expose
    private val backdrop_path: String? = null
    @SerializedName("name")
    @Expose
    private val name: String? = null
    @SerializedName("origin_country")
    @Expose
    private val origin_country: String? = null
}
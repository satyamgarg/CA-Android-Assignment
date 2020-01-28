package com.backbase.assignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCountries {
    @SerializedName("iso_3166_1")
    @Expose
    private val iso_3166_1: String? = null
    @SerializedName("name")
    @Expose
    private val name: String? = null
}
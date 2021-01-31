package com.example.muvi.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var movies: List<Movie>?,
    @SerializedName("total_pages")
    var totalPage: Int?,
    @SerializedName("total_results")
    var totalResult: Int?,
    @SerializedName("cast")
    var movieOfActor: List<Movie>?
)

package com.dicoding.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val movieId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String
)


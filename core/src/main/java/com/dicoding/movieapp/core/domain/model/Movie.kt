package com.dicoding.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val releaseDate: String,
    val popularity: Double,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val isFavorite: Boolean
) : Parcelable
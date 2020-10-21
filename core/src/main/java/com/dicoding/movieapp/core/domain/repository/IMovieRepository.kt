package com.dicoding.movieapp.core.domain.repository

import com.dicoding.movieapp.core.data.Resource
import com.dicoding.movieapp.core.domain.model.Movie
import io.reactivex.Flowable

interface IMovieRepository {

    fun getAllMovie(): Flowable<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flowable<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}
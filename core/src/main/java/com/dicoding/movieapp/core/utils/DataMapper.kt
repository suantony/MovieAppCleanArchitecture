package com.dicoding.movieapp.core.utils

import com.dicoding.movieapp.core.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.core.data.source.remote.response.MovieResponse
import com.dicoding.movieapp.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        isFavorite = input.isFavorite
    )

}
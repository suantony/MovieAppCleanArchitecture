package com.dicoding.movieapp.core.data.source.local

import com.dicoding.movieapp.core.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.core.data.source.local.room.MovieDao
import io.reactivex.Flowable

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flowable<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flowable<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}
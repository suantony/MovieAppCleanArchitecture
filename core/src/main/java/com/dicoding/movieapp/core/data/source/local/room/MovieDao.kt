package com.dicoding.movieapp.core.data.source.local.room

import androidx.room.*
import com.dicoding.movieapp.core.data.source.local.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>): Completable

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}

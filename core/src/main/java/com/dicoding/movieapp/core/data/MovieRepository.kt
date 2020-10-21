package com.dicoding.movieapp.core.data

import com.dicoding.movieapp.core.data.source.local.LocalDataSource
import com.dicoding.movieapp.core.data.source.remote.RemoteDataSource
import com.dicoding.movieapp.core.data.source.remote.network.ApiResponse
import com.dicoding.movieapp.core.data.source.remote.response.MovieResponse
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.repository.IMovieRepository
import com.dicoding.movieapp.core.utils.AppExecutors
import com.dicoding.movieapp.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {


    override fun getAllMovie(): Flowable<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flowable<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteMovie(): Flowable<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(tourismEntity, state) }
    }
}


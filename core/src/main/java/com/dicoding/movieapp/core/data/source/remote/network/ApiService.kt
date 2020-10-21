package com.dicoding.movieapp.core.data.source.remote.network

import com.dicoding.movieapp.core.data.source.remote.response.ListMovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getList(@Query("api_key") api_key: String): Flowable<ListMovieResponse>
}
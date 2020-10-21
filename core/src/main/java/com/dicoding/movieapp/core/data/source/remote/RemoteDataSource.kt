package com.dicoding.movieapp.core.data.source.remote

import android.util.Log
import com.dicoding.movieapp.core.BuildConfig
import com.dicoding.movieapp.core.data.source.remote.network.ApiResponse
import com.dicoding.movieapp.core.data.source.remote.network.ApiService
import com.dicoding.movieapp.core.data.source.remote.response.MovieResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val apiService: ApiService) {

    private val api_key = BuildConfig.API_KEY

    fun getAllMovie(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()

        val client = apiService.getList(api_key)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}


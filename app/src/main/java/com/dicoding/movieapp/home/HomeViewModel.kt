package com.dicoding.movieapp.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val tourism = LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllMovie())
}


package com.dicoding.movieapp.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteTourism = LiveDataReactiveStreams.fromPublisher(movieUseCase.getFavoriteMovie())
}


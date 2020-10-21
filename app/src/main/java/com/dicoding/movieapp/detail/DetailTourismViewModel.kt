package com.dicoding.movieapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase

class DetailTourismViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteTourism(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}

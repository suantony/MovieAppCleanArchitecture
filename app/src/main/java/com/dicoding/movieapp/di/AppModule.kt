package com.dicoding.movieapp.di

import com.dicoding.movieapp.core.domain.usecase.MovieInteractor
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import com.dicoding.movieapp.detail.DetailTourismViewModel
import com.dicoding.movieapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}


package com.dicoding.movieapp.core


import com.dicoding.movieapp.core.data.Resource
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.repository.IMovieRepository
import com.dicoding.movieapp.core.domain.usecase.MovieInteractor
import com.dicoding.movieapp.core.domain.usecase.MovieUseCase
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp() {
        movieUseCase = MovieInteractor(movieRepository)
        val dataFlowable = mock(Flowable::class.java) as Flowable<Resource<List<Movie>>>
        `when`(movieRepository.getAllMovie()).thenReturn(dataFlowable)
    }

    @Test
    fun getAllMovie() {
        movieUseCase.getAllMovie()

        verify(movieRepository).getAllMovie()
        verifyNoMoreInteractions(movieRepository)
    }
}
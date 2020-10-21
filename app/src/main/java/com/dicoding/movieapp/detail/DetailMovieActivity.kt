package com.dicoding.movieapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.movieapp.R
import com.dicoding.movieapp.core.domain.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val detailTourismViewModel: DetailTourismViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)

        val detailTourism = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailTourism(detailTourism)
    }

    private fun showDetailTourism(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            tv_detail_description.text = detailMovie.overview
            Glide.with(this@DetailMovieActivity)
                .load("https://image.tmdb.org/t/p/w500${detailMovie.backdropPath}")
                .into(text_detail_image)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailTourismViewModel.setFavoriteTourism(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}

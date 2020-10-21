package com.dicoding.movieapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movieapp.core.ui.MovieAdapter
import com.dicoding.movieapp.detail.DetailMovieActivity
import com.dicoding.movieapp.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteModule)

        val tourismAdapter = MovieAdapter()
        tourismAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteTourism.observe(this, Observer { dataTourism ->
            tourismAdapter.setData(dataTourism)
            view_empty.visibility = if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(rv_tourism) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tourismAdapter
        }

        supportActionBar?.title = getString(R.string.module_name)
    }
}
package com.dicoding.movieapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movieapp.core.R
import com.dicoding.movieapp.core.domain.model.Movie
import kotlinx.android.synthetic.main.item_list_movie.view.*
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                tv_item_title.text = movie.title
                tv_item_description.text = movie.overview
                tv_item_date.text = movie.releaseDate
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(img_poster)
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
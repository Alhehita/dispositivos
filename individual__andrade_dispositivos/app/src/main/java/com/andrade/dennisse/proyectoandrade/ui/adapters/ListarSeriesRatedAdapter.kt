package com.andrade.dennisse.proyectoandrade.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.ItemRatedSeriesInfoBinding
import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.rated.SeriesRatedInfoUI

class ListarSeriesRatedAdapter(    private val onClick: (SeriesRatedInfoUI) -> Unit
): ListAdapter<SeriesRatedInfoUI, ListarSeriesRatedAdapter.MovieVH>(DiffUtilFavoriteRatedSeriesCallback) {

    class MovieVH(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRatedSeriesInfoBinding.bind(view)

        fun render(item: SeriesRatedInfoUI,onClick: (SeriesRatedInfoUI) -> Unit) {
            binding.imageView.load("https://image.tmdb.org/t/p/w500" + item.poster_path)
            binding.titulo.text = item.name
            binding.tituloOriginal.text = item.original_name
            binding.userScore.apply {
                max = 100
                setProgress((item.vote_average * 10).toInt(), true)
            }
            binding.userScorePercentage.text = "${(item.vote_average * 10).toInt()}%"

            val maxPopularity = 200 // Ajusta este valor según la popularidad máxima en tu dataset
            binding.popularity.apply {
                max = 100
                setProgress((item.popularity / maxPopularity * 100).toInt(), true)
            }
            binding.popularityPercentage.text = "${(item.popularity / maxPopularity * 100).toInt()}%"

            itemView.setOnClickListener { onClick(item) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val inflater = LayoutInflater.from(parent.context)
        return MovieVH(
            inflater.inflate(
                R.layout.item_rated_series_info,
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.render(
            getItem(position), onClick
        )
    }
}

object DiffUtilFavoriteRatedSeriesCallback : DiffUtil.ItemCallback<SeriesRatedInfoUI>() {
    override fun areItemsTheSame(oldItem: SeriesRatedInfoUI, newItem: SeriesRatedInfoUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SeriesRatedInfoUI, newItem: SeriesRatedInfoUI): Boolean {
        return oldItem == newItem
    }
}

package com.andrade.dennisse.proyectoandrade.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.ItemMoviesInfoBinding
import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI

class ListarMoviesPopularityAdapter:
    ListAdapter<MoviesInfoUI,ListarMoviesPopularityAdapter.MovieVH>(DiffUtilMovieCallback){

        class MovieVH(view: View): RecyclerView.ViewHolder(view){
            private val binding= ItemMoviesInfoBinding.bind(view)

            fun render(item: MoviesInfoUI){
                binding.imageView.load("https://image.tmdb.org/t/p/w500"+item.poster_path)
                binding.titulo.text=item.title
                binding.titutloOriginal.text=item.original_title
                binding.idioma.text=item.original_language
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val inflater = LayoutInflater.from(parent.context)
        return MovieVH(
            inflater.inflate(
                R.layout.item_movies_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.render(
            getItem(position)
        )
    }


}

object DiffUtilMovieCallback : DiffUtil.ItemCallback<MoviesInfoUI>() {
    override fun areItemsTheSame(oldItem: MoviesInfoUI, newItem: MoviesInfoUI):
            Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: MoviesInfoUI, newItem: MoviesInfoUI):
            Boolean {
        return oldItem == newItem
    }

}
package com.andrade.dennisse.proyectoandrade.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.andrade.dennisse.proyectoandrade.R
import com.andrade.dennisse.proyectoandrade.databinding.ItemTvInfoBinding
import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.tv.TVInfoUI

class ListarTVPopularityAdapter(private val onClick: (TVInfoUI) -> Unit) :
    ListAdapter<TVInfoUI, ListarTVPopularityAdapter.TvVH>(DiffUtilTVCallback) {

    class TvVH(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTvInfoBinding.bind(view)

        fun render(item: TVInfoUI, onClick: (TVInfoUI) -> Unit) {
            binding.imageView.load("https://image.tmdb.org/t/p/w500" + item.poster_path)
            binding.titulo.text = item.title
            binding.titutloOriginal.text = item.original_title

            itemView.setOnClickListener { onClick(item) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvVH {
        val inflater = LayoutInflater.from(parent.context)
        return TvVH(
            inflater.inflate(
                R.layout.item_tv_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvVH, position: Int) {
        holder.render(
            getItem(position),onClick
        )
    }


}

object DiffUtilTVCallback : DiffUtil.ItemCallback<TVInfoUI>() {
    override fun areItemsTheSame(oldItem: TVInfoUI, newItem: TVInfoUI):
            Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: TVInfoUI, newItem: TVInfoUI):
            Boolean {
        return oldItem == newItem
    }

}
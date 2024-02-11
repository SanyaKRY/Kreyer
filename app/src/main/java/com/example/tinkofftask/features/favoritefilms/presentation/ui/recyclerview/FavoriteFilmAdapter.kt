package com.example.tinkofftask.features.favoritefilms.presentation.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.tinkofftask.databinding.FilmItemBinding
import com.example.tinkofftask.features.favoritefilms.presentation.model.FavoriteFilmUi
import javax.inject.Inject

class FavoriteFilmAdapter @Inject constructor() : ListAdapter<FavoriteFilmUi, ViewHolderFavoriteFilm>(
    FavoriteFilmsDiffCallback()
)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoriteFilm {
        val itemViewHolder = FilmItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolderFavoriteFilm(itemViewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderFavoriteFilm, position: Int) {
        holder.apply {
            val current: FavoriteFilmUi = getItem(position)
            bind(current)
        }
    }
}
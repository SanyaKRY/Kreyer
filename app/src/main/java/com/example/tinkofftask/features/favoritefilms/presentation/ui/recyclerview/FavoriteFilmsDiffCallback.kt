package com.example.tinkofftask.features.favoritefilms.presentation.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.tinkofftask.features.favoritefilms.presentation.model.FavoriteFilmUi

class FavoriteFilmsDiffCallback : DiffUtil.ItemCallback<FavoriteFilmUi>() {

    override fun areItemsTheSame(oldItem: FavoriteFilmUi, newItem: FavoriteFilmUi): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: FavoriteFilmUi, newItem: FavoriteFilmUi): Boolean {
        return oldItem == newItem
    }
}
package com.example.tinkofftask.features.mainscreen.presentation.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.tinkofftask.features.mainscreen.presentation.model.FilmUi

class FilmDiffCallback : DiffUtil.ItemCallback<FilmUi>() {

    override fun areItemsTheSame(oldItem: FilmUi, newItem: FilmUi): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: FilmUi, newItem: FilmUi): Boolean {
        return oldItem == newItem
    }
}
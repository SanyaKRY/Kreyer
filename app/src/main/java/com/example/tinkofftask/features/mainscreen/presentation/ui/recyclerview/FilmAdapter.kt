package com.example.tinkofftask.features.mainscreen.presentation.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tinkofftask.databinding.FilmItemBinding
import com.example.tinkofftask.features.mainscreen.presentation.model.FilmUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class FilmAdapter @AssistedInject constructor(
    @Assisted("filmDetailListener") private val filmDetailListener: (
        filmUi: FilmUi
    ) -> Unit,
    @Assisted("insertDeleteFilmListener") private val insertDeleteFilmListener: (
        filmUi: FilmUi
    ) -> Unit
) : ListAdapter<FilmUi, ViewHolderFilm>(
    FilmDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilm {
        val itemViewHolder = FilmItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolderFilm(itemViewHolder)
        setItemListener(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderFilm, position: Int) {
        holder.apply {
            val current: FilmUi = getItem(position)
            bind(current)
        }
    }

    private fun setItemListener(viewHolderFilm: ViewHolderFilm) {
        // ClickListener
        viewHolderFilm.itemView.setOnClickListener {
            val position = viewHolderFilm.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                filmDetailListener.invoke(getItem(position))
            }
        }
        // LongClickListener
        viewHolderFilm.itemView.setOnLongClickListener {
            val position = viewHolderFilm.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                insertDeleteFilmListener.invoke(getItem(position))
                getItem(position).apply {
                    isSavedToDataBase = !isSavedToDataBase
                }
                viewHolderFilm.animateFavoriteIcon( getItem(position).isSavedToDataBase)
            }
            true
        }
    }
}
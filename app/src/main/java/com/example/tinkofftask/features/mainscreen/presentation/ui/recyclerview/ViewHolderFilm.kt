package com.example.tinkofftask.features.mainscreen.presentation.ui.recyclerview

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tinkofftask.R
import com.example.tinkofftask.databinding.FilmItemBinding
import com.example.tinkofftask.features.mainscreen.presentation.model.FilmUi

class ViewHolderFilm(private val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(filmUi: FilmUi) {
        binding.filmName.text = filmUi.nameRu

        val year = filmUi.year
        val mainGenre = filmUi.genres[0]
        val filmGenreAndYear = "$mainGenre ($year)"
        binding.filmGenreAndYear.text = filmGenreAndYear
        loadImage(binding.filmImage, filmUi.posterUrlPreview)
    }

    private fun loadImage(imageView: AppCompatImageView, imageUri: String) {
        Glide.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
            .override(170, 230)
            .into(imageView)
    }
}
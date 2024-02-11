package com.example.tinkofftask.features.favoritefilms.presentation.ui.recyclerview

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tinkofftask.R
import com.example.tinkofftask.databinding.FilmItemBinding
import com.example.tinkofftask.features.favoritefilms.presentation.model.FavoriteFilmUi

class ViewHolderFavoriteFilm(
    private val binding: FilmItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(filmUi: FavoriteFilmUi) {
        binding.filmName.text = filmUi.nameRu
        val year = filmUi.year
        val mainGenre = filmUi.genre
        val filmGenreAndYear = "$mainGenre ($year)"
        binding.filmGenreAndYear.text = filmGenreAndYear
        loadImage(binding.filmImage, filmUi.posterUrl)
        if (filmUi.isSavedToDataBase) {
            binding.star.setImageResource(R.drawable.ic_star_full)
        } else {
            binding.star.setImageResource(R.drawable.ic_star_border)
        }
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
package com.example.tinkofftask.features.mainscreen.presentation.ui.recyclerview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewPropertyAnimator
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

    fun animateFavoriteIcon(isFavoriteBeer: Boolean) {
        val viewPropertyAnimator = binding.star.animate()
        viewPropertyAnimator
            .scaleX(0.5f)
            .scaleY(0.5f)
            .setDuration(250)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    populateFavoriteIconView(isFavoriteBeer)
                    restartFavoriteIconSize(viewPropertyAnimator)
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    binding.star.isClickable = false
                }
            })
    }

    // animation
    private fun populateFavoriteIconView(isFavorite: Boolean) {
        getFavoriteIcon(isFavorite)?.let {
            binding.star.setImageResource(it)
        }

    }

    // animation
    private fun getFavoriteIcon(isFavorite: Boolean): Int? {
        return if (isFavorite) {
            R.drawable.ic_star_full
        } else {
            R.drawable.ic_star_border
        }
    }

    // animation
    private fun restartFavoriteIconSize(viewPropertyAnimator: ViewPropertyAnimator) {
        viewPropertyAnimator
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(250)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    viewPropertyAnimator.cancel()
                    binding.star.isClickable = true
                }
            })
    }
}
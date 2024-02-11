package com.example.tinkofftask.features.favoritefilms.presentation.model

import java.lang.Exception

data class FavoriteFilmsScreenState(
    val listOfFilms: List<FavoriteFilmUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null)

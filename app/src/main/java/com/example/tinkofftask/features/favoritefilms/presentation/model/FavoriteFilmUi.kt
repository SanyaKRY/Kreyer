package com.example.tinkofftask.features.favoritefilms.presentation.model

data class FavoriteFilmUi(
    val filmId: Int,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val genre: String,
    var isSavedToDataBase: Boolean
)

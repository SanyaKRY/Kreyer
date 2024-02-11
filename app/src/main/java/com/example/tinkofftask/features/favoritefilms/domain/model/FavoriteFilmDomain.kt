package com.example.tinkofftask.features.favoritefilms.domain.model

data class FavoriteFilmDomain(
    val filmId: Int,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val genre: String,
    var isSavedToDataBase: Boolean
)

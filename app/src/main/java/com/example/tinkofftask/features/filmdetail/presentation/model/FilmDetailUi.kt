package com.example.tinkofftask.features.filmdetail.presentation.model

data class FilmDetailUi(
    val kinopoiskId: Int,
    val description: String,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: List<String>,
    val countries: List<String>
)

package com.example.tinkofftask.features.mainscreen.domain.model

data class FilmDomain(
    val filmId: Int,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: List<String>,
    val countries: List<String>
)

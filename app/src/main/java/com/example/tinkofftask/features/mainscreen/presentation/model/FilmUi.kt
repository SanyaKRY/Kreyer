package com.example.tinkofftask.features.mainscreen.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmUi(
    val filmId: Int,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: List<String>,
    val countries: List<String>
): Parcelable

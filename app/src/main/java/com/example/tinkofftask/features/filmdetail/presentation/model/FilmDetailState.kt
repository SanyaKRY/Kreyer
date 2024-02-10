package com.example.tinkofftask.features.filmdetail.presentation.model

import java.lang.Exception

data class FilmDetailState(
    val filmDetail: FilmDetailUi? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null
)
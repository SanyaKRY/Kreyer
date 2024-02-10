package com.example.tinkofftask.features.mainscreen.presentation.model

import java.lang.Exception

data class MainScreenState(
    val listOfFilms: List<FilmUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null
)

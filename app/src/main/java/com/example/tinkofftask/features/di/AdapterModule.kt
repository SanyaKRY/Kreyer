package com.example.tinkofftask.features.di

import com.example.tinkofftask.features.mainscreen.presentation.model.FilmUi
import com.example.tinkofftask.features.mainscreen.presentation.ui.recyclerview.FilmAdapter
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AdapterModule {

    fun createFilmAdapter(
        userProjectDetailListener: (filmUi: FilmUi) -> Unit
    ): FilmAdapter
}
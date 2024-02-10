package com.example.tinkofftask.features.mainscreen.domain

import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain
import com.example.tinkofftask.core.datatype.Result

interface MainScreenRepository {

    suspend fun getListOfFilms(): Result<List<FilmDomain>>
}
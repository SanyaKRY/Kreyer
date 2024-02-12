package com.example.tinkofftask.features.mainscreen.domain

import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.data.datasource.database.model.FavoriteFilmTable

interface MainScreenRepository {

    suspend fun getListOfFilms(page: Int): Result<List<FilmDomain>>

    suspend fun insertToDataBase(film: FilmDomain): Result<Unit>

    suspend fun deleteByFilmIdFromDataBase(film: FilmDomain): Result<Unit>

    fun getFavoriteFilms(): Result<List<FavoriteFilmTable>>
}
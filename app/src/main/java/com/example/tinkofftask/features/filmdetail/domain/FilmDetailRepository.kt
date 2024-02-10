package com.example.tinkofftask.features.filmdetail.domain

import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.filmdetail.domain.model.FilmDetailDomain

interface FilmDetailRepository {

    suspend fun getFilmDetail(filmId: Int): Result<FilmDetailDomain>
}
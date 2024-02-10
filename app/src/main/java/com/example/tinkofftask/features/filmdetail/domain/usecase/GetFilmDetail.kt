package com.example.tinkofftask.features.filmdetail.domain.usecase

import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.filmdetail.domain.FilmDetailRepository
import com.example.tinkofftask.features.filmdetail.domain.model.FilmDetailDomain
import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import javax.inject.Inject

class GetFilmDetail @Inject constructor(
    private val filmDetailRepository: FilmDetailRepository
) {

    suspend fun execute(filmId: Int): Result<FilmDetailDomain> {
        return filmDetailRepository.getFilmDetail(filmId)
    }
}
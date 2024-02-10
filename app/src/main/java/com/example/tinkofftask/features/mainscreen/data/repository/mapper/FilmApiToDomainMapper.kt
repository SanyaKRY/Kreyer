package com.example.tinkofftask.features.mainscreen.data.repository.mapper

import com.example.tinkofftask.features.mainscreen.data.datasource.api.model.FilmApi
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain

object FilmApiToDomainMapper {
    fun map(type: List<FilmApi>): List<FilmDomain> {
        return type.map {
            FilmDomain(
                filmId = it.filmId,
                year = it.year,
                nameRu = it.nameRu,
                posterUrl = it.posterUrlPreview,
                posterUrlPreview = it.posterUrlPreview,
                genres = it.genres.map { genres -> genres.genre },
                countries = it.countries.map { countries -> countries.country }
            )
        }
    }
}
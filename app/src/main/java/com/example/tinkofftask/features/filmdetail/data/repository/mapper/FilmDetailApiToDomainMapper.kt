package com.example.tinkofftask.features.filmdetail.data.repository.mapper

import com.example.tinkofftask.features.filmdetail.data.datasource.api.model.FilmDetailApi
import com.example.tinkofftask.features.filmdetail.domain.model.FilmDetailDomain

object FilmDetailApiToDomainMapper {
    fun map(type: FilmDetailApi): FilmDetailDomain {
        return FilmDetailDomain(
            kinopoiskId = type.kinopoiskId,
            description = type.description,
            year = type.year,
            nameRu = type.nameRu,
            posterUrl = type.posterUrlPreview,
            posterUrlPreview = type.posterUrlPreview,
            genres = type.genres.map { genres -> genres.genre },
            countries = type.countries.map { countries -> countries.country }
        )
    }
}
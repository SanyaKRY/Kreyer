package com.example.tinkofftask.features.filmdetail.presentation.mapper

import com.example.tinkofftask.features.filmdetail.domain.model.FilmDetailDomain
import com.example.tinkofftask.features.filmdetail.presentation.model.FilmDetailUi

object FilmDetailDomainToUiMapper {
    fun map(type: FilmDetailDomain): FilmDetailUi {
        return FilmDetailUi(
            kinopoiskId = type.kinopoiskId,
            description = type.description,
            year = type.year,
            nameRu = type.nameRu,
            posterUrl = type.posterUrlPreview,
            posterUrlPreview = type.posterUrlPreview,
            genres = type.genres,
            countries = type.countries
        )
    }
}
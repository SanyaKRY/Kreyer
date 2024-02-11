package com.example.tinkofftask.features.favoritefilms.presentation.mapper

import com.example.tinkofftask.features.favoritefilms.domain.model.FavoriteFilmDomain
import com.example.tinkofftask.features.favoritefilms.presentation.model.FavoriteFilmUi

object FavoriteFilmsDomainToUiMapper {
    fun map(type: List<FavoriteFilmDomain>): List<FavoriteFilmUi> {
        return type.map {
            FavoriteFilmUi(
                filmId = it.filmId,
                year = it.year,
                nameRu = it.nameRu,
                posterUrl = it.posterUrl,
                genre = it.genre,
                isSavedToDataBase = true
            )
        }
    }
}
package com.example.tinkofftask.features.favoritefilms.data.repository.mapper

import com.example.tinkofftask.features.favoritefilms.domain.model.FavoriteFilmDomain
import com.example.tinkofftask.features.mainscreen.data.datasource.database.model.FavoriteFilmTable

object FavoriteFilmDataBaseToDomainMapper {
    fun map(type: List<FavoriteFilmTable>): List<FavoriteFilmDomain> {
        return type.map {
            FavoriteFilmDomain(
                filmId = it.filmId,
                year = it.year,
                nameRu = it.nameRu,
                posterUrl = it.posterUrl,
                genre = it.genre,
                isSavedToDataBase = false
            )
        }
    }
}
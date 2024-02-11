package com.example.tinkofftask.features.favoritefilms.domain

import kotlinx.coroutines.flow.Flow
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.favoritefilms.domain.model.FavoriteFilmDomain

interface FavoriteFilmsScreenRepository {

    fun getFavoriteFilms(): Flow<Result<List<FavoriteFilmDomain>>>
}
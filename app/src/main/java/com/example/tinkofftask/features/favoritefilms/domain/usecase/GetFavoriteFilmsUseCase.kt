package com.example.tinkofftask.features.favoritefilms.domain.usecase

import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.favoritefilms.domain.FavoriteFilmsScreenRepository
import com.example.tinkofftask.features.favoritefilms.domain.model.FavoriteFilmDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteFilmsUseCase @Inject constructor(
    private val favoriteFilmsScreenRepository: FavoriteFilmsScreenRepository
) {

    fun execute(): Flow<Result<List<FavoriteFilmDomain>>> {
        return favoriteFilmsScreenRepository.getFavoriteFilms()
    }
}
package com.example.tinkofftask.features.favoritefilms.data.repository

import com.example.tinkofftask.features.favoritefilms.domain.FavoriteFilmsScreenRepository
import com.example.tinkofftask.features.mainscreen.data.datasource.database.FavoriteFilmDataBaseDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.favoritefilms.data.repository.mapper.FavoriteFilmDataBaseToDomainMapper
import com.example.tinkofftask.features.favoritefilms.domain.model.FavoriteFilmDomain

class FavoriteFilmsScreenRepositoryImpl @Inject constructor(
    private val favoriteFilmDataBaseDataSource: FavoriteFilmDataBaseDataSource
) : FavoriteFilmsScreenRepository {

    override fun getFavoriteFilms(): Flow<Result<List<FavoriteFilmDomain>>> {
        return favoriteFilmDataBaseDataSource.getAllFavoriteFilmFlow().map { result ->
            when (result) {
                is Result.Success -> Result.Success(FavoriteFilmDataBaseToDomainMapper.map(result.data))
                is Result.Error -> Result.Error(result.error)
                is Result.Loading -> Result.Loading
            }
        }
    }
}
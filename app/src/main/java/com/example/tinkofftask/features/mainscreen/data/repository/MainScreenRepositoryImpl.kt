package com.example.tinkofftask.features.mainscreen.data.repository

import com.example.tinkofftask.features.mainscreen.data.datasource.api.FilmsNetworkDataSource
import com.example.tinkofftask.features.mainscreen.data.datasource.api.model.FilmsApi
import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.data.datasource.database.FavoriteFilmDataBaseDataSource
import com.example.tinkofftask.features.mainscreen.data.datasource.database.model.FavoriteFilmTable
import com.example.tinkofftask.features.mainscreen.data.repository.mapper.FilmApiToDomainMapper
import com.example.tinkofftask.features.mainscreen.data.repository.mapper.FilmDomainToDataBaseMapper

class MainScreenRepositoryImpl @Inject constructor(
    private val filmsNetworkDataSource: FilmsNetworkDataSource,
    private val favoriteFilmDataBaseDataSource: FavoriteFilmDataBaseDataSource
) : MainScreenRepository {

    override suspend fun getListOfFilms(page: Int): Result<List<FilmDomain>> {
            return when (val result: Result<FilmsApi> = filmsNetworkDataSource.getListOfFilms(page)) {
            is Result.Success -> Result.Success(FilmApiToDomainMapper.map(result.data.films))
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading
        }
    }

    override suspend fun searchFilmByKey(keyword: String): Result<List<FilmDomain>> {
        return when (val result: Result<FilmsApi> = filmsNetworkDataSource.searchFilmByKey(keyword)) {
            is Result.Success -> Result.Success(FilmApiToDomainMapper.map(result.data.films))
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading
        }
    }

    override suspend fun insertToDataBase(film: FilmDomain): Result<Unit> {
        return favoriteFilmDataBaseDataSource.insert(FilmDomainToDataBaseMapper.map(film))
    }

    override suspend fun deleteByFilmIdFromDataBase(film: FilmDomain): Result<Unit> {
        return favoriteFilmDataBaseDataSource.deleteByFilmId(FilmDomainToDataBaseMapper.map(film))
    }

    override fun getFavoriteFilms(): Result<List<FavoriteFilmTable>> {
        return when (val result: Result<List<FavoriteFilmTable>> = favoriteFilmDataBaseDataSource.getFavoriteFilms()) {
            is Result.Success -> Result.Success(result.data)
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading
        }
    }
}
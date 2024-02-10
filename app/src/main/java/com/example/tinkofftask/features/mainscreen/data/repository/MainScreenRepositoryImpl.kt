package com.example.tinkofftask.features.mainscreen.data.repository

import com.example.tinkofftask.features.mainscreen.data.datasource.api.FilmsNetworkDataSource
import com.example.tinkofftask.features.mainscreen.data.datasource.api.model.FilmsApi
import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.data.repository.mapper.FilmApiToDomainMapper

class MainScreenRepositoryImpl @Inject constructor(
    private val filmsNetworkDataSource: FilmsNetworkDataSource
) : MainScreenRepository {

    override suspend fun getListOfFilms(): Result<List<FilmDomain>> {
            return when (val result: Result<FilmsApi> = filmsNetworkDataSource.getListOfFilms()) {
            is Result.Success -> Result.Success(FilmApiToDomainMapper.map(result.data.films))
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading
        }
    }
}
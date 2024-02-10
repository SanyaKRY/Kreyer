package com.example.tinkofftask.features.filmdetail.data.repository

import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.filmdetail.data.datasource.api.FilmDetailNetworkDataSource
import com.example.tinkofftask.features.filmdetail.data.datasource.api.model.FilmDetailApi
import com.example.tinkofftask.features.filmdetail.data.repository.mapper.FilmDetailApiToDomainMapper
import com.example.tinkofftask.features.filmdetail.domain.FilmDetailRepository
import com.example.tinkofftask.features.filmdetail.domain.model.FilmDetailDomain
import javax.inject.Inject

class FilmDetailRepositoryImpl @Inject constructor(
    private val filmDetailNetworkDataSource: FilmDetailNetworkDataSource
) : FilmDetailRepository {

    override suspend fun getFilmDetail(filmId: Int): Result<FilmDetailDomain> {
        return when (val result: Result<FilmDetailApi> = filmDetailNetworkDataSource.getFilmDetail(filmId)) {
            is Result.Success -> Result.Success(FilmDetailApiToDomainMapper.map(result.data))
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading
        }
    }
}
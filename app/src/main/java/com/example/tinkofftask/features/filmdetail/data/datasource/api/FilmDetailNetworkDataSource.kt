package com.example.tinkofftask.features.filmdetail.data.datasource.api

import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.filmdetail.data.datasource.api.model.FilmDetailApi
import com.example.tinkofftask.features.filmdetail.data.datasource.api.retrofit.FilmDetailApiService
import java.lang.Exception
import javax.inject.Inject

class FilmDetailNetworkDataSource @Inject constructor(
    private val filmDetailApiService: FilmDetailApiService
) {

    suspend fun getFilmDetail(filmId: Int): Result<FilmDetailApi> {
        return try {
            val filmDetail = filmDetailApiService.getFilmDetail(filmId)
            Result.Success(filmDetail)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}
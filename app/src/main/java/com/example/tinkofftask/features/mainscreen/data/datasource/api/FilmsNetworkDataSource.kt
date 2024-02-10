package com.example.tinkofftask.features.mainscreen.data.datasource.api

import com.example.tinkofftask.features.mainscreen.data.datasource.api.model.FilmsApi
import com.example.tinkofftask.features.mainscreen.data.datasource.api.retrofit.FilmsApiService
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result;
import java.lang.Exception

class FilmsNetworkDataSource @Inject constructor(
    private val filmsApiService: FilmsApiService
) {

    suspend fun getListOfFilms(): Result<FilmsApi> {
        return try {
            val listOfFilms = filmsApiService.getListOfFilms()
            Result.Success(listOfFilms)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}
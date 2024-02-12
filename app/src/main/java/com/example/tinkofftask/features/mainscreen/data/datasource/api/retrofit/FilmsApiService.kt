package com.example.tinkofftask.features.mainscreen.data.datasource.api.retrofit

import com.example.tinkofftask.features.mainscreen.data.datasource.api.model.FilmsApi
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import com.example.tinkofftask.BuildConfig

interface FilmsApiService {

    @Headers("X-API-KEY: ${BuildConfig.X_API_KEY}")
    @GET("/api/v2.2/films/top")
    suspend fun getListOfFilms(
        @Query("page") page: Int
    ): FilmsApi
}
package com.example.tinkofftask.features.filmdetail.data.datasource.api.retrofit

import com.example.tinkofftask.BuildConfig
import com.example.tinkofftask.features.filmdetail.data.datasource.api.model.FilmDetailApi
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FilmDetailApiService {

    @Headers("X-API-KEY: ${BuildConfig.X_API_KEY}")
    @GET("/api/v2.2/films/{filmId}")
    suspend fun getFilmDetail(
        @Path("filmId") filmId: Int
    ): FilmDetailApi
}
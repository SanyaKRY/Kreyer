package com.example.tinkofftask.features.filmdetail.data.datasource.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmDetailApi(
    @Json(name = "kinopoiskId") val kinopoiskId: Int,
    @Json(name = "year") val year: String,
    @Json(name = "description") val description: String,
    @Json(name = "nameRu") val nameRu: String,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "posterUrlPreview") val posterUrlPreview: String,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "countries") val countries: List<Country>
)

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "genre") val genre: String,
)

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "country") val country: String,
)

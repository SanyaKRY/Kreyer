package com.example.tinkofftask.features.di

import com.example.tinkofftask.features.filmdetail.data.datasource.api.retrofit.FilmDetailApiService
import com.example.tinkofftask.features.mainscreen.data.datasource.api.retrofit.FilmsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val URL_BASE = "https://kinopoiskapiunofficial.tech/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideFilmsApiService(retrofit: Retrofit): FilmsApiService =
        retrofit.create(FilmsApiService::class.java)

    @Provides
    @Singleton
    fun provideFilmDetailApiService(retrofit: Retrofit): FilmDetailApiService =
        retrofit.create(FilmDetailApiService::class.java)
}
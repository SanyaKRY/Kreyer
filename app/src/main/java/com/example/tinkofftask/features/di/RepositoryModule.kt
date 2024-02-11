package com.example.tinkofftask.features.di

import com.example.tinkofftask.features.favoritefilms.data.repository.FavoriteFilmsScreenRepositoryImpl
import com.example.tinkofftask.features.favoritefilms.domain.FavoriteFilmsScreenRepository
import com.example.tinkofftask.features.filmdetail.data.repository.FilmDetailRepositoryImpl
import com.example.tinkofftask.features.filmdetail.domain.FilmDetailRepository
import com.example.tinkofftask.features.mainscreen.data.repository.MainScreenRepositoryImpl
import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainScreenRepository(
        mainScreenRepositoryImpl: MainScreenRepositoryImpl
    ): MainScreenRepository

    @Binds
    @Singleton
    abstract fun bindFilmDetailRepository(
        filmDetailRepositoryImpl: FilmDetailRepositoryImpl
    ): FilmDetailRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteFilmsScreenRepository(
        favoriteFilmsScreenRepositoryImpl: FavoriteFilmsScreenRepositoryImpl
    ): FavoriteFilmsScreenRepository
}
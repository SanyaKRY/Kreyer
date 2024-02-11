package com.example.tinkofftask.features.di

import android.content.Context
import androidx.room.Room
import com.example.tinkofftask.features.mainscreen.data.datasource.database.FavoriteFilmDatabase
import com.example.tinkofftask.features.mainscreen.data.datasource.database.dao.FavoriteFilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavoriteFilmDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteFilmDatabase::class.java,
            "favorite_film_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteFilmDao(dataBase: FavoriteFilmDatabase): FavoriteFilmDao =
        dataBase.favoriteFilmDao
}
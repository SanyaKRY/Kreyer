package com.example.tinkofftask.features.mainscreen.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tinkofftask.features.mainscreen.data.datasource.database.dao.FavoriteFilmDao
import com.example.tinkofftask.features.mainscreen.data.datasource.database.model.FavoriteFilmTable

@Database(entities = arrayOf(FavoriteFilmTable::class), version = 1, exportSchema = false)
abstract class FavoriteFilmDatabase : RoomDatabase() {

    abstract val favoriteFilmDao: FavoriteFilmDao
}
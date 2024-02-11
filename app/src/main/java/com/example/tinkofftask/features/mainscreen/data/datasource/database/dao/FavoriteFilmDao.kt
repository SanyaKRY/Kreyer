package com.example.tinkofftask.features.mainscreen.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tinkofftask.features.mainscreen.data.datasource.database.model.FavoriteFilmTable
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFilmDao {

    @Insert
    suspend fun insert(film: FavoriteFilmTable): Long

    @Query("DELETE FROM favorite_film_table WHERE filmId = :filmId")
    suspend fun deleteByFilmId(filmId: Int): Int

    @Query("SELECT * FROM favorite_film_table")
    fun getAllFavoriteFilm(): List<FavoriteFilmTable>

    @Query("SELECT * FROM favorite_film_table")
    fun getAllFavoriteFilmFlow(): Flow<List<FavoriteFilmTable>>
}
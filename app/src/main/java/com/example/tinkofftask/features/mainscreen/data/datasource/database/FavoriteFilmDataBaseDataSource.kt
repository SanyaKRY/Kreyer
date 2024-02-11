package com.example.tinkofftask.features.mainscreen.data.datasource.database

import com.example.tinkofftask.features.mainscreen.data.datasource.database.dao.FavoriteFilmDao
import com.example.tinkofftask.features.mainscreen.data.datasource.database.model.FavoriteFilmTable
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FavoriteFilmDataBaseDataSource @Inject constructor(
    private val favoriteFilmDao: FavoriteFilmDao
) {

    suspend fun insert(film: FavoriteFilmTable): Result<Unit> {
        return try {
            val result = favoriteFilmDao.insert(film)
            Result.Success(Unit)
        } catch (ex: Exception) {
            Result.Error(Exception(ex))
        }
    }

    suspend fun deleteByFilmId(film: FavoriteFilmTable): Result<Unit> {
        return try {
            val result = favoriteFilmDao.deleteByFilmId(film.filmId)
            Result.Success(Unit)
        } catch (ex: Exception) {
            Result.Error(Exception(ex))
        }
    }

    fun getFavoriteFilms(): Result<List<FavoriteFilmTable>> {
        return try {
            val result = favoriteFilmDao.getAllFavoriteFilm()
            Result.Success(result)
        } catch (ex: Exception) {
            Result.Error(Exception(ex))
        }
    }

    fun getAllFavoriteFilmFlow(): Flow<Result<List<FavoriteFilmTable>>> {
        return favoriteFilmDao.getAllFavoriteFilmFlow().map {
            Result.Success(it) as Result<List<FavoriteFilmTable>>
        }.catch {
            emit(Result.Error(Exception(it)))
        }
    }
}
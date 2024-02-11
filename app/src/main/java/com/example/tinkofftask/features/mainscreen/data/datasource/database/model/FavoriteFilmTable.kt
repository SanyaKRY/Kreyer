package com.example.tinkofftask.features.mainscreen.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_film_table")
data class FavoriteFilmTable(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    val filmId: Int,
    val posterUrl: String
)

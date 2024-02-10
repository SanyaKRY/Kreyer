package com.example.tinkofftask.features.filmdetail.presentation.event

interface FilmDetailEvent

class GetFilmDetailEvent(val filmId: Int) : FilmDetailEvent

class ReloadFilmDetailEvent(val filmId: Int) : FilmDetailEvent

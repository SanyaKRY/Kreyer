package com.example.tinkofftask.features.mainscreen.presentation.event

import com.example.tinkofftask.features.mainscreen.presentation.model.FilmUi

interface MainScreenEvent

class ReloadListOfFilms : MainScreenEvent

class InsertFilmToDataBase(val film: FilmUi) : MainScreenEvent
class DeleteFilmFromDataBase(val film: FilmUi) : MainScreenEvent
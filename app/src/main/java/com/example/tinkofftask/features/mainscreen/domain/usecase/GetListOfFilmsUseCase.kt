package com.example.tinkofftask.features.mainscreen.domain.usecase

import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain

class GetListOfFilmsUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(): Result<List<FilmDomain>> {

        return when (val result: Result<List<FilmDomain>> = mainScreenRepository.getListOfFilms()) {
            is Result.Success -> {
                val favoriteFilmId = mutableListOf<Int>()
                when (val resultFavoriteFilm = mainScreenRepository.getFavoriteFilms()) {
                    is Result.Success -> {
                        resultFavoriteFilm.data.map {
                            favoriteFilmId.add(it.filmId)
                            it.filmId
                        }
                    }
                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
                result.data.forEach{
                    if (favoriteFilmId.contains(it.filmId)) {
                        it.isSavedToDataBase = true
                    }
                }
                Result.Success(result.data)
            }
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading
        }
    }
}
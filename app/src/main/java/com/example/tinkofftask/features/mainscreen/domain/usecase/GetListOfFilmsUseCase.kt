package com.example.tinkofftask.features.mainscreen.domain.usecase

import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain

class GetListOfFilmsUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(): Result<List<FilmDomain>> {

        val totalListOfFilms = mutableListOf<FilmDomain>()

        for(i in 1..5) {
            when (val result: Result<List<FilmDomain>> = mainScreenRepository.getListOfFilms(i)) {
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
                    totalListOfFilms.addAll(result.data)
                }
                is Result.Error ->  return Result.Error(result.error)
                is Result.Loading -> return Result.Loading
            }
        }
        return Result.Success(totalListOfFilms)
    }
}
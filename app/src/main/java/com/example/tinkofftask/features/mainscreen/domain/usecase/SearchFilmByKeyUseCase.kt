package com.example.tinkofftask.features.mainscreen.domain.usecase

import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain
import javax.inject.Inject

class SearchFilmByKeyUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(keyword: String): Result<List<FilmDomain>>{
        return mainScreenRepository.searchFilmByKey(keyword)
    }
}
package com.example.tinkofftask.features.mainscreen.domain.usecase

import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain

class GetListOfFilmsUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(): Result<List<FilmDomain>> {
        return mainScreenRepository.getListOfFilms()
    }
}
package com.example.tinkofftask.features.mainscreen.domain.usecase

import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.domain.MainScreenRepository
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain
import javax.inject.Inject

class DeleteFilmByFilmIdFromDataBaseUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(film: FilmDomain): Result<Unit> {
        return mainScreenRepository.deleteByFilmIdFromDataBase(film)
    }
}
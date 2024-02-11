package com.example.tinkofftask.features.mainscreen.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkofftask.features.mainscreen.domain.model.FilmDomain
import com.example.tinkofftask.features.mainscreen.domain.usecase.GetListOfFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.mainscreen.domain.usecase.DeleteFilmByFilmIdFromDataBaseUseCase
import com.example.tinkofftask.features.mainscreen.domain.usecase.InsertFilmToDataBaseUseCase
import com.example.tinkofftask.features.mainscreen.presentation.event.DeleteFilmFromDataBase
import com.example.tinkofftask.features.mainscreen.presentation.event.InsertFilmToDataBase
import com.example.tinkofftask.features.mainscreen.presentation.event.MainScreenEvent
import com.example.tinkofftask.features.mainscreen.presentation.event.ReloadListOfFilms
import com.example.tinkofftask.features.mainscreen.presentation.mapper.FilmDomainToUiMapper
import com.example.tinkofftask.features.mainscreen.presentation.mapper.FilmUiToDomainMapper
import com.example.tinkofftask.features.mainscreen.presentation.model.FilmUi
import com.example.tinkofftask.features.mainscreen.presentation.model.MainScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getListOfFilmsUseCase: GetListOfFilmsUseCase,
    private val insertFilmToDataBaseUseCase: InsertFilmToDataBaseUseCase,
    private val deleteFilmByFilmIdFromDataBaseUseCase: DeleteFilmByFilmIdFromDataBaseUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState(isLoading = true))
    val stateFlow: Flow<MainScreenState>
        get() = _stateFlow

    init {
        getListOfFilms()
    }

    fun handleIntent(event: MainScreenEvent) {
        when (event) {
            is ReloadListOfFilms -> reloadListOfFilms()
            is InsertFilmToDataBase -> insertFilmToDataBase(event.film)
            is DeleteFilmFromDataBase -> deleteFilmFromDataBase(event.film)
        }
    }

    private fun insertFilmToDataBase(film: FilmUi) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFilmToDataBaseUseCase.execute(FilmUiToDomainMapper.map(film))
        }
    }

    private fun deleteFilmFromDataBase(film: FilmUi) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFilmByFilmIdFromDataBaseUseCase.execute(FilmUiToDomainMapper.map(film))
        }
    }

    private fun reloadListOfFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value =
                _stateFlow.value.copy(listOfFilms = emptyList(), isLoading = true, error = null)
            getListOfFilms()
        }
    }

    private fun getListOfFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2_000)
            val result: Result<List<FilmDomain>> = getListOfFilmsUseCase.execute()
            withContext(Dispatchers.Main) {
                Log.d("LogCat", "Result: $result")
                when (result) {
                    is Result.Success -> {
                        val listOfFilms = FilmDomainToUiMapper.map(result.data)
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfFilms = listOfFilms, isLoading = false, error = null)
                    }
                    is Result.Error -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfFilms = emptyList(), isLoading = false, error = result.error)
                    }
                    is Result.Loading -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfFilms = emptyList(), isLoading = true, error = null)
                    }
                }
            }
        }
    }
}
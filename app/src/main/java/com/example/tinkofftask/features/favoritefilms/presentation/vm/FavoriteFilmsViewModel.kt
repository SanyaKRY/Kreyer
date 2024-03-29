package com.example.tinkofftask.features.favoritefilms.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkofftask.features.favoritefilms.domain.model.FavoriteFilmDomain
import com.example.tinkofftask.features.favoritefilms.domain.usecase.GetFavoriteFilmsUseCase
import com.example.tinkofftask.features.favoritefilms.presentation.model.FavoriteFilmsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.favoritefilms.domain.usecase.SearchFavoriteFilmByName
import com.example.tinkofftask.features.favoritefilms.presentation.mapper.FavoriteFilmsDomainToUiMapper

@HiltViewModel
class FavoriteFilmsViewModel  @Inject constructor(
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
    private val searchFavoriteFilmByName: SearchFavoriteFilmByName
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<FavoriteFilmsScreenState> =
        MutableStateFlow(FavoriteFilmsScreenState(isLoading = true))
    val stateFlow: Flow<FavoriteFilmsScreenState>
        get() = _stateFlow

    init {
        getFavoriteFilmsByDatabase()
    }

    fun searchFavoriteFilmByName(searchQuery: String) {
        Log.d("CatLog", "searchQuery: $searchQuery")
        viewModelScope.launch(Dispatchers.IO) {
            val flow: Flow<Result<List<FavoriteFilmDomain>>> = searchFavoriteFilmByName.execute(searchQuery)
            withContext(Dispatchers.Main) {
                flow.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            val list = FavoriteFilmsDomainToUiMapper.map(result.data)
                            Log.d("CatLog", "$list")
                            _stateFlow.value =
                                _stateFlow.value.copy(listOfFilms = list, isLoading = false)
                        }
                        is Result.Error -> {
                            // very sad :(
                        }
                        is Result.Loading -> {
                            // very sad :(
                        }
                    }
                }
            }
        }
    }

    private fun getFavoriteFilmsByDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2_000)
            val flow: Flow<Result<List<FavoriteFilmDomain>>> = getFavoriteFilmsUseCase.execute()
            withContext(Dispatchers.Main) {
                flow.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            val list = FavoriteFilmsDomainToUiMapper.map(result.data)
                            _stateFlow.value =
                                _stateFlow.value.copy(listOfFilms = list, isLoading = false)
                        }
                        is Result.Error -> {
                            _stateFlow.value =
                                _stateFlow.value.copy(listOfFilms = emptyList(), isLoading = false, error = result.error)
                        }
                        is Result.Loading -> {
                            // very sad :(
                        }
                    }
                }
            }
        }
    }
}
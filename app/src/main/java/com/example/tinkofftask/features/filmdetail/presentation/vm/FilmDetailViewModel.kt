package com.example.tinkofftask.features.filmdetail.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkofftask.core.datatype.Result
import com.example.tinkofftask.features.filmdetail.domain.model.FilmDetailDomain
import com.example.tinkofftask.features.filmdetail.domain.usecase.GetFilmDetail
import com.example.tinkofftask.features.filmdetail.presentation.event.FilmDetailEvent
import com.example.tinkofftask.features.filmdetail.presentation.event.GetFilmDetailEvent
import com.example.tinkofftask.features.filmdetail.presentation.event.ReloadFilmDetailEvent
import com.example.tinkofftask.features.filmdetail.presentation.mapper.FilmDetailDomainToUiMapper
import com.example.tinkofftask.features.filmdetail.presentation.model.FilmDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getFilmDetail: GetFilmDetail
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<FilmDetailState> =
        MutableStateFlow(FilmDetailState(isLoading = true))
    val stateFlow: Flow<FilmDetailState>
        get() = _stateFlow

    fun handleIntent(event: FilmDetailEvent) {
        when (event) {
            is GetFilmDetailEvent -> getFilmDetail(event.filmId)
            is ReloadFilmDetailEvent -> reloadFilmDetail(event.filmId)
        }
    }

    private fun reloadFilmDetail(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value =
                _stateFlow.value.copy(filmDetail = null, isLoading = true, error = null)
            getFilmDetail(filmId)
        }
    }

    private fun getFilmDetail(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2_000)
            val result: Result<FilmDetailDomain> = getFilmDetail.execute(filmId)
            withContext(Dispatchers.Main) {
                Log.d("LogCat", "Result: $result")
                when (result) {
                    is Result.Success -> {
                        val filmDetail = FilmDetailDomainToUiMapper.map(result.data)
                        _stateFlow.value =
                            _stateFlow.value.copy(filmDetail = filmDetail, isLoading = false)
                    }
                    is Result.Error -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(filmDetail = null, isLoading = false, error = result.error)
                    }
                    is Result.Loading -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(filmDetail = null, isLoading = true, error = null)
                    }
                }
            }
        }
    }
}
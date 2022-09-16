package com.example.movieapp.feature_movieapp.presentation.screens.movie_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.toMovieDetails
import com.example.movieapp.feature_movieapp.domain.model.NetworkResponse
import com.example.movieapp.feature_movieapp.domain.use_case.UseCase
import com.example.movieapp.feature_movieapp.domain.util.Constants.MOVIE_ARGUMENT_ID
import com.example.movieapp.feature_movieapp.domain.util.ResultsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val useCases: UseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedMovie: MutableStateFlow<ResultsState?> =
        MutableStateFlow(ResultsState(true, null, false))
    val selectedMovie: StateFlow<ResultsState?> = _selectedMovie

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val movieId = savedStateHandle.get<Int>(MOVIE_ARGUMENT_ID)
            val response = movieId?.let { useCases.movieDetailsUseCae(movieId) }
            when (response) {
                is NetworkResponse.Success -> {
                    _selectedMovie.emit(ResultsState(false, response.value.toMovieDetails(), false))
                }
                is NetworkResponse.Failure -> {
                    _selectedMovie.emit(ResultsState(false, null, false))
                }
                else -> {}
            }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>){
        _colorPalette.value = colors
    }

}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}
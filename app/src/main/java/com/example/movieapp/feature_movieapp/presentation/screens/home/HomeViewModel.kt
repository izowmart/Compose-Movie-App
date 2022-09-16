package com.example.movieapp.feature_movieapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.example.movieapp.feature_movieapp.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase: UseCase
) : ViewModel() {
    val getAllMovies = useCase.moviesUseCase()
}
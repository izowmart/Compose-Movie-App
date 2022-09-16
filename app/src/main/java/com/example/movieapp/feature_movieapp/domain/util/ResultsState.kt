package com.example.movieapp.feature_movieapp.domain.util

import com.example.movieapp.feature_movieapp.domain.model.MovieDetails

data class ResultsState(
    val isLoading: Boolean = false,
    val data: MovieDetails? = null,
    val error: Boolean = false
)

package com.example.movieapp.feature_movieapp.domain.model

import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.Genre

// Todo
data class MovieDetails(
    val title: String,
    val image: String,
    val vote: String,
    val release_date: String?,
    val overView: String,
    val genres: List<Genre>
)
package com.example.movieapp.feature_movieapp.data.remote.dto.movieDto

data class MovieResult(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
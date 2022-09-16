package com.example.movieapp.feature_movieapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val movies: List<Movie> = emptyList(),
    val lastUpdated: Long? = null
)

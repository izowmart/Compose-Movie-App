package com.example.movieapp.feature_movieapp.domain.use_case

import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.MovieDetailsResult
import com.example.movieapp.feature_movieapp.domain.model.NetworkResponse
import com.example.movieapp.feature_movieapp.domain.repository.MoviesRepository

class MovieDetailsUseCae(private val repository: MoviesRepository) {
    suspend operator fun invoke(id: Int): NetworkResponse<MovieDetailsResult> {
        return repository.getMovieDetails(id = id)
    }
}





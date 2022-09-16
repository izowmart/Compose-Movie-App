package com.example.movieapp.feature_movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.MovieDetailsResult
import com.example.movieapp.feature_movieapp.domain.model.Movie
import com.example.movieapp.feature_movieapp.domain.model.MovieDetails
import com.example.movieapp.feature_movieapp.domain.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getAllMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(id: Int): NetworkResponse<MovieDetailsResult>
}

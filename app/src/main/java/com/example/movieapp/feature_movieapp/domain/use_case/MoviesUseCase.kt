package com.example.movieapp.feature_movieapp.domain.use_case

import androidx.paging.PagingData
import com.example.movieapp.feature_movieapp.domain.model.Movie
import com.example.movieapp.feature_movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesUseCase (private val repository : MoviesRepository) {
    operator fun invoke(): Flow<PagingData<Movie>>{
        return repository.getAllMovies()
    }
}





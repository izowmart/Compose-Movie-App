package com.example.movieapp.feature_movieapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.feature_movieapp.data.local.MyDatabase
import com.example.movieapp.feature_movieapp.data.paging_source.MovieRemoteMediator
import com.example.movieapp.feature_movieapp.data.remote.MoviesApi
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.MovieDetailsResult
import com.example.movieapp.feature_movieapp.domain.model.Movie
import com.example.movieapp.feature_movieapp.domain.model.NetworkResponse
import com.example.movieapp.feature_movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val database: MyDatabase
) : MoviesRepository, BaseRepository() {

    override fun getAllMovies(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = {database.movieDao.getAllMovies()}
        return Pager(
            config = PagingConfig(pageSize = 8),
            remoteMediator = MovieRemoteMediator(
                moviesApi = moviesApi,
                myDatabase = database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getMovieDetails(id: Int): NetworkResponse<MovieDetailsResult> {
        return safeApiCall { moviesApi.getMovieDetails(id = id) }
    }
}
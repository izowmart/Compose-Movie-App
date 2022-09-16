package com.example.movieapp.feature_movieapp.data.remote

import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.MovieDetailsResult
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDto.MovieResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("page") page: Int? = null,
    ): MovieResult

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsResult

}
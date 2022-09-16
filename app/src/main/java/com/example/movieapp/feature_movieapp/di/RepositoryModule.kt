package com.example.movieapp.feature_movieapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.movieapp.feature_movieapp.data.local.MyDatabase
import com.example.movieapp.feature_movieapp.data.remote.MoviesApi
import com.example.movieapp.feature_movieapp.data.repository.MoviesRepositoryImpl
import com.example.movieapp.feature_movieapp.domain.repository.MoviesRepository
import com.example.movieapp.feature_movieapp.domain.use_case.MovieDetailsUseCae
import com.example.movieapp.feature_movieapp.domain.use_case.MoviesUseCase
import com.example.movieapp.feature_movieapp.domain.use_case.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideMoviesRepository(database: MyDatabase, moviesApi: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(moviesApi,database)
    }

    @Provides
    @Singleton
    fun provideMainUseCase(moviesRepository: MoviesRepository): UseCase {
        return UseCase(
            movieDetailsUseCae = MovieDetailsUseCae(moviesRepository),
            moviesUseCase = MoviesUseCase(moviesRepository)
        )
    }
}
package com.example.movieapp.feature_movieapp.data.paging_source

import androidx.paging.*
import androidx.room.withTransaction
import com.example.movieapp.feature_movieapp.data.local.MyDatabase
import com.example.movieapp.feature_movieapp.data.remote.MoviesApi
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDto.toMovie
import com.example.movieapp.feature_movieapp.domain.model.Movie
import com.example.movieapp.feature_movieapp.domain.model.MovieRemoteKeys
import java.io.IOException
import java.util.concurrent.TimeUnit


@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    val moviesApi: MoviesApi,
    val myDatabase: MyDatabase
) : RemoteMediator<Int, Movie>() {
    private val movieDao = myDatabase.movieDao

//    override suspend fun initialize(): InitializeAction {
//        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
//        val lastUpdated = movieDao.getSelectedMovie(movieId = 579974).lastUpdated ?: 0L
//        return if (System.currentTimeMillis() - lastUpdated >= cacheTimeout) {
//            // Cached data is up-to-date, so there is no need to re-fetch from network.
//            InitializeAction.SKIP_INITIAL_REFRESH
//        } else {
//            // Need to refresh cached data from network; returning LAUNCH_INITIAL_REFRESH here
//            // will also block RemoteMediator's APPEND and PREPEND from running until REFRESH
//            // succeeds.
//            InitializeAction.LAUNCH_INITIAL_REFRESH
//        }
//    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> null

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id
                }

            }
//              val searchPage = if (page!! > 500) 400 else page

                val response = moviesApi.getAllMovies(page = page)
                // Here we are mapping the network result
                val list = response.results.map { movieResult ->
                    movieResult.toMovie()
                }

                myDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        movieDao.deletedAllMovie()
                    }
                    // Insert new users into database, which invalidates the current
                    // PagingData, allowing Paging to present the updates in the DB.
                    movieDao.addMovies(list)
                }

            MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}
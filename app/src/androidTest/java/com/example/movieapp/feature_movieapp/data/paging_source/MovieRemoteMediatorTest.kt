package com.example.movieapp.feature_movieapp.data.paging_source

import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import com.example.movieapp.feature_movieapp.data.local.MyDatabase
import com.example.movieapp.feature_movieapp.data.remote.MoviesApi
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDto.MovieResult
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDto.toMovie
import com.example.movieapp.feature_movieapp.domain.model.Movie
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.openMocks
import java.io.FileInputStream

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
class MovieRemoteMediatorTest {

    @Mock
    private lateinit var moviesApi: MoviesApi
    private lateinit var myDatabase: MyDatabase
    private lateinit var remoteMediator: MovieRemoteMediator
    private val gson = Gson()
    private lateinit var movieResult: MovieResult
    private lateinit var movies: List<Movie>


    @Before
    fun setup() {
        openMocks(this)


        val listBytes = FileInputStream("src/main/assets/list.json").readBytes()

        movieResult = gson.fromJson(String(listBytes), MovieResult::class.java)
        movies = movieResult.results.map {
            it.toMovie()
        }

        myDatabase = MyDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )

    }

    @After
    fun cleanUp() {
        myDatabase.clearAllTables()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = MovieRemoteMediator(
                moviesApi = moviesApi,
                myDatabase = myDatabase
            )
            val pagingState = PagingState<Int, Movie>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }


}
package com.example.movieapp.feature_movieapp.data.paging_source

import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.Genre
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.MovieDetailsResult
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto.toMovieDetails
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDto.MovieResult
import com.example.movieapp.feature_movieapp.data.remote.dto.movieDto.toMovie
import com.example.movieapp.feature_movieapp.domain.model.Movie
import com.example.movieapp.feature_movieapp.domain.model.MovieDetails
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.FileInputStream

@RunWith(MockitoJUnitRunner::class)
class ResultsConversion {

    private val gson = Gson()
    private lateinit var movieDetailsResult: MovieDetailsResult
    private lateinit var movie: Movie

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        val detailBytes = FileInputStream("src/main/assets/detail.json").readBytes()
        val listBytes = FileInputStream("src/main/assets/list.json").readBytes()

        movieDetailsResult = gson.fromJson(String(detailBytes), MovieDetailsResult::class.java)
        movie = gson.fromJson(String(listBytes), MovieResult::class.java).results[0].toMovie()

    }

    @Test
    fun test_movie_detail_conversion_should_pass() {
        val movieDetails = movieDetailsResult.toMovieDetails()
        val expectedMovieDetail = MovieDetails(
            title = "Fall",
            release_date = "2022",
            image = "https://image.tmdb.org/t/p/w500/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg",
            overView = "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero.",
            vote = "7.9",
            genres = listOf(
                Genre(28, "Action"),
                Genre(35, "Comedy"),
                Genre(878, "Science Fiction")
            )


        )
        assertEquals(expectedMovieDetail, movieDetails)
    }

    @Test
    fun test_movies_conversion_should_pass() {
        val movieView = movie
        val expectedMovieView = Movie(
            title = "Fall",
            release_date = "2022",
            image = "https://image.tmdb.org/t/p/w500/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg",
            id = 985939,
            backdrop_path = "/2RSirqZG949GuRwN38MYCIGG4Od.jpg",
            original_language = "en",
            original_title = "Fall",
            overview = "For best friends Becky and Hunter, life is all about conquering fears and pushing limits. But after they climb 2,000 feet to the top of a remote, abandoned radio tower, they find themselves stranded with no way down. Now Becky and Hunter’s expert climbing skills will be put to the ultimate test as they desperately fight to survive the elements, a lack of supplies, and vertigo-inducing heights",
            popularity = 7631.138,
            video = false,
            vote_average = "7.4",
            vote_count = 574,
            lastUpdated = System.currentTimeMillis()

        )
        assertEquals(expectedMovieView, movieView)
    }
}
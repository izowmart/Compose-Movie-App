package com.example.movieapp.feature_movieapp.data.remote.dto.movieDto

import com.example.movieapp.feature_movieapp.domain.model.Movie
import com.example.movieapp.feature_movieapp.domain.util.Constants.IMAGE_URL

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
// converter
fun Result.toMovie(): Movie {
    return Movie(
        id = id,
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        image = IMAGE_URL + poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average.toString(),
        vote_count = vote_count,
        lastUpdated = System.currentTimeMillis()
    )
}
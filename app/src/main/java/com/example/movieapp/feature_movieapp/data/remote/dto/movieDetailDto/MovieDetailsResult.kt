package com.example.movieapp.feature_movieapp.data.remote.dto.movieDetailDto

import com.example.movieapp.feature_movieapp.domain.model.MovieDetails
import com.example.movieapp.feature_movieapp.domain.util.Constants.IMAGE_URL

data class MovieDetailsResult(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String?,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

//converter function
fun MovieDetailsResult.toMovieDetails():MovieDetails{
    return MovieDetails(
        title = title,
        image = IMAGE_URL + poster_path,
        vote = vote_average.toString(),
        release_date = release_date?.substringBefore("-") ?: "",
        overView = overview,
        genres = genres
    )
}

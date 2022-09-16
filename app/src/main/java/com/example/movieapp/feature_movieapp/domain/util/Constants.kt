package com.example.movieapp.feature_movieapp.domain.util


object Constants {

    const val MOVIE_ARGUMENT_ID = "movieId"

    const val MOVIE_DATABASE = "movie_database"
    const val MOVIE_DATABASE_TABLE = "movie_table"
    const val MOVIE_REMOTE_KEYS_DATABASE_TABLE = "movie_remote_keys_table"

    const val PREFERENCES_NAME = "movie_preferences"
    //    check if its first time use
    const val IS_ONBOARDING_COMPLETED = "Is_boarding_completed"

    const val CACHE_NAME = "MovieCache"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    // Field from default config.
    const val BASE_URL = "https://api.themoviedb.org/3/"

    // Field from default config.
    val KEY = Pair("api_key", "15e383204c1b8a09dbfaaa4c01ed7e17")

    const val ON_BOARDING_PAGE_COUNT = 3
    const val LAST_ON_BOARDING_PAGE = 2

    const val ITEMS_PER_PAGE = 8
    const val ABOUT_TEXT_MAX_LINES = 7

    const val MIN_BACKGROUND_IMAGE_HEIGHT = 0.4f
}
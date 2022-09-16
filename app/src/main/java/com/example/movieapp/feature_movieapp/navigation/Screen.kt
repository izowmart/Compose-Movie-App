package com.example.movieapp.feature_movieapp.navigation

sealed class Screen(val route: String){
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object MovieDetails: Screen("movie_details/{movieId}"){
        fun passMovieId(movieId: Int): String{
            return "movie_details/$movieId"
        }
    }
}
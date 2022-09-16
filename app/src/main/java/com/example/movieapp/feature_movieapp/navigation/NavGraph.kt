package com.example.movieapp.feature_movieapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.feature_movieapp.domain.util.Constants.MOVIE_ARGUMENT_ID
import com.example.movieapp.feature_movieapp.presentation.screens.home.HomeScreen
import com.example.movieapp.feature_movieapp.presentation.screens.movie_details.MovieDetailsScreen
import com.example.movieapp.feature_movieapp.presentation.screens.search.SearchScreen
import com.example.movieapp.feature_movieapp.presentation.screens.splash_screen.SplashScreen
import com.example.movieapp.feature_movieapp.presentation.screens.welcome.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.MovieDetails.route,
            arguments = listOf(navArgument(name = MOVIE_ARGUMENT_ID) {
                type = NavType.IntType
            })
        ) {
            MovieDetailsScreen(navController = navController)
        }
    }
}
package com.example.movieapp.feature_movieapp.presentation.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.feature_movieapp.navigation.Screen
import com.example.movieapp.feature_movieapp.presentation.common.ListContent
import com.example.movieapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allMovies = homeViewModel.getAllMovies.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colors.statusBarColor
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor
        )
    }

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
            ListContent(movies = allMovies, navController = navController)
        }
    )
}
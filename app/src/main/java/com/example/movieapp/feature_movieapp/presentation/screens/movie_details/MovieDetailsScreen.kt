package com.example.movieapp.feature_movieapp.presentation.screens.movie_details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.feature_movieapp.domain.util.PaletteGenerator.convertImageUrlToBitmap
import com.example.movieapp.feature_movieapp.domain.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val selectedMovie by movieDetailsViewModel.selectedMovie.collectAsState()
    val colorPalette by movieDetailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            selectedMovie = selectedMovie?.data,
            colors = colorPalette
        )
    } else {
        movieDetailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        movieDetailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "${selectedMovie?.data?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        movieDetailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(bitmap = bitmap)
                        )
                    }
                }
            }
        }
    }

}
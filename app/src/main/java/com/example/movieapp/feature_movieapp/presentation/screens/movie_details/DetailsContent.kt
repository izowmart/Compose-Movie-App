package com.example.movieapp.feature_movieapp.presentation.screens.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.feature_movieapp.domain.model.MovieDetails
import com.example.movieapp.feature_movieapp.domain.util.Constants.ABOUT_TEXT_MAX_LINES
import com.example.movieapp.feature_movieapp.presentation.components.InfoBox
import com.example.movieapp.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedMovie: MovieDetails?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedMovie) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(android.graphics.Color.parseColor(darkVibrant))
        )
    }

    selectedMovie?.let {
        BackgroundContent(
            selectedMovie = it,
            infoBoxIconColor = Color(android.graphics.Color.parseColor(vibrant)),
            sheetGroundColor = Color(android.graphics.Color.parseColor(darkVibrant)),
            contentColor = Color(android.graphics.Color.parseColor(onDarkVibrant)),
            movieImage = it.image,
            backgroundColor = Color(android.graphics.Color.parseColor(darkVibrant)),
            onCloseClicked = { navController.popBackStack() },
        )
    }

}

@Composable
fun BackgroundContent(
    movieImage: String,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit,
    selectedMovie: MovieDetails,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetGroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            // load image using coin library
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = movieImage)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .build(),
                contentDescription = "movie image",
                contentScale = ContentScale.Crop
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(
                    modifier = Modifier.padding(all = SMALL_PADDING),
                    onClick = { onCloseClicked() }
                ) {
                    Icon(
                        modifier = Modifier.size(INFO_ICON_SIZE),
                        imageVector = Icons.Default.Close,
                        contentDescription = "close icon",
                        tint = Color.White
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(sheetGroundColor)
                .padding(all = SMALL_PADDING)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LARGE_PADDING),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = androidx.compose.ui.Modifier
                        .size(EXTRA_LARGE_PADDING)
                        .weight(2f),
                    painter = painterResource(id = R.drawable.power),
                    contentDescription = "logo",
                    tint = contentColor
                )
                Text(
                    modifier = Modifier.weight(8f),
                    text = selectedMovie.title,
                    color = contentColor,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MEDIUM_PADDING),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoBox(
                    icon = painterResource(id = com.google.android.material.R.drawable.ic_clock_black_24dp),
                    iconColor = infoBoxIconColor,
                    bigText = selectedMovie.vote,
                    smallText = "vote",
                    textColor = contentColor
                )
                InfoBox(
                    icon = painterResource(id = com.google.android.material.R.drawable.ic_keyboard_black_24dp),
                    iconColor = infoBoxIconColor,
                    bigText = selectedMovie.release_date ?: "Month",
                    smallText = "Month",
                    textColor = contentColor
                )
                InfoBox(
                    icon = painterResource(id = R.drawable.ic_network_error),
                    iconColor = infoBoxIconColor,
                    bigText = selectedMovie.vote,
                    smallText = "Vote",
                    textColor = contentColor
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Movie Synopsis",
                color = contentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .padding(bottom = MEDIUM_PADDING),
                text = selectedMovie.overView,
                color = contentColor,
                fontSize = MaterialTheme.typography.body1.fontSize,
                maxLines = ABOUT_TEXT_MAX_LINES
            )


        }
    }


}

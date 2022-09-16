package com.example.movieapp.feature_movieapp.domain.model

import androidx.annotation.DrawableRes
import com.example.movieapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
){
    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Movie fan? If you are then we have a great news for you!"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favorite movies and learn some of the things that you didn't know about."
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Ratings",
        description = "Check out your favorite movies' rating and  see how much loved they are comparing to others."
    )

}

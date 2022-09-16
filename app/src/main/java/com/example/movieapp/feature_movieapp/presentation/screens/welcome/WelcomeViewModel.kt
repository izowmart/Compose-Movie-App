package com.example.movieapp.feature_movieapp.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import com.example.movieapp.feature_movieapp.data.local.MySharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val pref: MySharedPreferences
) : ViewModel() {
    fun saveOnBoardingState(completed: Boolean) {
        pref.saveOnBoardingState(completed = completed)
    }
}
package com.example.movieapp.feature_movieapp.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.movieapp.feature_movieapp.domain.util.Constants.IS_ONBOARDING_COMPLETED
import com.example.movieapp.feature_movieapp.domain.util.Constants.PREFERENCES_NAME


class MySharedPreferences(context: Context) {

    private val settings: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0)

    fun saveOnBoardingState(completed: Boolean) {
        settings.edit()
            .putBoolean(IS_ONBOARDING_COMPLETED, completed)
            .apply()
    }

    fun readOnBoardingState(): Boolean {
        return settings.getBoolean(IS_ONBOARDING_COMPLETED, false)
    }
}
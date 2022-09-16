package com.example.movieapp.feature_movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.feature_movieapp.data.local.MyDatabase
import com.example.movieapp.feature_movieapp.data.local.MySharedPreferences
import com.example.movieapp.feature_movieapp.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        MySharedPreferences(context)

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MyDatabase::class.java,
        Constants.MOVIE_DATABASE
    ).build()

}
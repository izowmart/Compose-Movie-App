package com.example.movieapp.feature_movieapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.feature_movieapp.domain.util.Constants.MOVIE_REMOTE_KEYS_DATABASE_TABLE


//@Entity(tableName = MOVIE_REMOTE_KEYS_DATABASE_TABLE)
data class MovieRemoteKeys(
//    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)


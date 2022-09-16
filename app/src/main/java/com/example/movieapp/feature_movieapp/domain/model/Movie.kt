package com.example.movieapp.feature_movieapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.feature_movieapp.domain.util.Constants.IMAGE_URL
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val image:String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: String,
    val vote_count: Int,
    val lastUpdated: Long?


)

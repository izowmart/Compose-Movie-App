package com.example.movieapp.feature_movieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.feature_movieapp.data.local.dao.MovieDao
import com.example.movieapp.feature_movieapp.domain.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){

    companion object{
        fun create(context: Context,useInMemory:Boolean): MyDatabase{
            val databaseBuilder = if(useInMemory){
                Room.inMemoryDatabaseBuilder(context,MyDatabase::class.java)
            }else{
                Room.databaseBuilder(context,MyDatabase::class.java,"test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }

    }
    abstract val movieDao:MovieDao

}
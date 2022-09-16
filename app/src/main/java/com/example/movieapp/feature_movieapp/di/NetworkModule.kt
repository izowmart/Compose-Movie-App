package com.example.movieapp.feature_movieapp.di

import android.app.Application
import com.example.movieapp.BuildConfig
import com.example.movieapp.feature_movieapp.data.remote.MoviesApi
import com.example.movieapp.feature_movieapp.domain.util.Constants
import com.example.movieapp.feature_movieapp.domain.util.Constants.BASE_URL
import com.example.movieapp.feature_movieapp.domain.util.Constants.CACHE_NAME
import com.example.movieapp.feature_movieapp.domain.util.Constants.KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    private val apiInterceptor = Interceptor {
        val request = it.request().newBuilder()
        val originalHttpUrl = it.request().url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(KEY.first, KEY.second)
            .build()
        request.url(url)
        it.proceed(request.build())
    }

    private val cacheInterceptor = Interceptor { chain ->
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(30, TimeUnit.DAYS)
            .build()
        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

    @Provides
    @Singleton
    fun providesOKHttpClient(cache: Cache): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) okHttpClient.addInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCache(app: Application): Cache {
        return Cache(File(app.applicationContext.cacheDir, CACHE_NAME), 10485760L)
    }
}






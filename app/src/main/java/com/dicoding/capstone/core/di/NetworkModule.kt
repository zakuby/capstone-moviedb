package com.dicoding.capstone.core.di

import com.dicoding.capstone.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.dicoding.capstone.core.data.remote.service.TheMovieDbServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    private val gson = GsonBuilder().create()

    private val interceptor: Interceptor =
        Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val builderNew = original.newBuilder().apply {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/json")
                url(originalHttpUrl)
            }
            val requestNew = builderNew.build()
            chain.proceed(requestNew)
        }

    private val httpLogging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .followRedirects(false)
            .addInterceptor(interceptor)
            .apply { if (BuildConfig.DEBUG) addInterceptor(httpLogging) }
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideMovieServices(retrofit: Retrofit): TheMovieDbServices = retrofit.create(TheMovieDbServices::class.java)
}
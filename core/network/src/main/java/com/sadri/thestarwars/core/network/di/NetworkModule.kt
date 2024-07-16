package com.sadri.thestarwars.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sadri.thestarwars.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val API_BASE_URL = "https://swapi.dev/api/"
    private const val JSON_CONTENT_TYPE = "application/json"
    private const val NETWORK_TIME_OUT_IN_SECONDS = 15L

    @Provides
    fun providesHttpLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesOkHttpBuilder(
        httpLoggingInterceptor: Interceptor,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return okHttpClientBuilder
            .readTimeout(NETWORK_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .callTimeout(NETWORK_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttp: OkHttpClient,
        networkJson: Json,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttp)
            .addConverterFactory(networkJson.asConverterFactory(JSON_CONTENT_TYPE.toMediaType()))
            .build()
    }
}
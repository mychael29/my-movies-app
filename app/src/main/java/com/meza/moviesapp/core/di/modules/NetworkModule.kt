package com.meza.moviesapp.core.di.modules

import com.meza.data.network.service.MoviesServiceImpl
import com.meza.data.network.apis.MoviesApi
import com.meza.data.network.service.MoviesService
import com.meza.domain.Constants.URL_BASE
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun serviceApi(moviesApi: MoviesApi): MoviesService =
        MoviesServiceImpl(moviesApi)

    @Provides
    @Singleton
    fun authApi(retrofit: Retrofit): MoviesApi = retrofit.create(
        MoviesApi::class.java)

    @Provides
    @Singleton
    fun retrofitInterface(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()
            return@addInterceptor it.proceed(request)
        }.build())
        .build()
}
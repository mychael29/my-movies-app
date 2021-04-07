package com.meza.moviesapp.core.di.modules

import com.meza.data.mapper.*
import com.meza.domain.repository.AuthRepository
import com.meza.data.repository.AuthRepositoryImpl
import com.meza.data.network.service.MoviesService
import com.meza.data.repository.MoviesRepositoryImpl
import com.meza.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(service: MoviesService, authMapper: AuthMapper): AuthRepository {
        return AuthRepositoryImpl(
            service,
            authMapper
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(service: MoviesService, moviesMapper: MoviesMapper): MoviesRepository {
        return MoviesRepositoryImpl(
            service,
            moviesMapper
        )
    }

    @Provides
    @Singleton
    fun provideAuthMapper(): AuthMapper {
        return AuthMapperImpl()
    }

    @Provides
    @Singleton
    fun provideMovieMapper(): MoviesMapper {
        return MoviesMapperImpl()
    }
}
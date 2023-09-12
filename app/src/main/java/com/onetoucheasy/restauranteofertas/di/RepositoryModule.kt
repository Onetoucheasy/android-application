package com.onetoucheasy.restauranteofertas.di

import com.onetoucheasy.restauranteofertas.repository.DefaultRepository
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.repository.remote.DefaultRemoteDataSource
import com.onetoucheasy.restauranteofertas.repository.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract  fun bindsRepository(defaultRepository: DefaultRepository) : Repository

    @Binds
    abstract fun bindsRemoteDataSource(defaultRemoteDataSource: DefaultRemoteDataSource) : RemoteDataSource
}
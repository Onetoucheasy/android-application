package com.onetoucheasy.restauranteofertas.di

import com.onetoucheasy.restauranteofertas.repository.remote.OneTouchApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                    .apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                        level = HttpLoggingInterceptor.Level.HEADERS
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            ).build()
    }
    //TODO Change base URL in provideRetrofit
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("http://10.0.2.3/api/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Provides
    fun provideApi(retrofit:Retrofit): OneTouchApi{
        return retrofit.create(OneTouchApi::class.java)
    }
}
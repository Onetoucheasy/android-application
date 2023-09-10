package com.onetoucheasy.restauranteofertas.utils

import com.onetoucheasy.restauranteofertas.repository.remote.OneTouchApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseNetworkMockTest {

    private lateinit var api: OneTouchApi
    private lateinit var mockWebServer: MockWebServer
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = OneTouchApiMockDispatcher()
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(OneTouchApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}

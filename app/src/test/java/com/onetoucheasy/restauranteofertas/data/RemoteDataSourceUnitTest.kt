package com.onetoucheasy.restauranteofertas.data

import android.util.Log
import com.onetoucheasy.restauranteofertas.domain.model.UserType
import com.onetoucheasy.restauranteofertas.repository.remote.DefaultRemoteDataSource
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.utils.BaseNetworkMockTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteDataSourceUnitTest : BaseNetworkMockTest() {

    @Test
    fun nameTest() = runTest {

        // Given
        val userType: UserType = UserType.CUSTOMER
        val getSignUpRequestBody = SignUpRequestBody("Maria","maria@prueba.com","password0",userType.toString())

        // Then
        assert(getSignUpRequestBody.name == "Maria")
    }  // pass

    @Test // falla
    fun myTest() = runTest {
        // Given
        val remoteDataSource = DefaultRemoteDataSource(api)

        // When
//        val actual = remoteDataSource.getRestaurants()
//        val actual = remoteDataSource.getOfferById("b2e21a5e-958f-4ab8-84fe-7d78b63b9101")
//        Log.d("Tag", "actual.restaurants.first(): ${remoteDataSource}") // OfferNameMock1-1

        // Then
//        assert(actual.restaurants.first().name == "Pizza Pera")
    }
}
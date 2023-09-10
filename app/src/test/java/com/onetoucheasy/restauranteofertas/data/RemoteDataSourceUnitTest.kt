package com.onetoucheasy.restauranteofertas.data

import com.onetoucheasy.restauranteofertas.domain.model.UserType
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
    }
}
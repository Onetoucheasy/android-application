package com.onetoucheasy.restauranteofertas.data

import com.onetoucheasy.restauranteofertas.data.remote.fakes.FakeRemoteDataSource
import com.onetoucheasy.restauranteofertas.repository.DefaultRepository
import com.onetoucheasy.restauranteofertas.repository.mappers.RemoteToLocalMapper
import com.onetoucheasy.restauranteofertas.repository.remote.RemoteDataSource
import com.onetoucheasy.restauranteofertas.repository.remote.request.SignUpRequestBody
import com.onetoucheasy.restauranteofertas.utils.generateJWTResponse
import com.onetoucheasy.restauranteofertas.utils.generateJWTResponseUser
import com.onetoucheasy.restauranteofertas.utils.generateOfferById
import com.onetoucheasy.restauranteofertas.utils.generateOfferList
import com.onetoucheasy.restauranteofertas.utils.generateRestaurantList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DefaultRepositoryTest {
    // Dependencies
    // SUT
    private lateinit var repositoryImpl: DefaultRepository

    // Dependencies
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var  remoteToLocalMapper: RemoteToLocalMapper

    @Before
    fun setup() {
        remoteToLocalMapper = RemoteToLocalMapper()
    }

    @Test // Test with Fakes
    fun `WHEN performLogin with invalid data EXPECT error`() = runTest {
        // GIVEN
        remoteDataSource = FakeRemoteDataSource()
        repositoryImpl =
            DefaultRepository(remoteDataSource, remoteToLocalMapper)

        // WHEN
        val actual = repositoryImpl.performLogin("")

        // THEN
        assert(actual!=null)
    }

    @Test // Test with Mockks
    fun `WHEN performLogin expect company`() = runTest {
        // GIVEN
        remoteDataSource = mockk()
        repositoryImpl =
            DefaultRepository(remoteDataSource, remoteToLocalMapper)
        coEvery { remoteDataSource.performLogin(any()) } returns generateJWTResponse()

        // WHEN
        val actual = repositoryImpl.performLogin("")

        // THEN
        assert(actual!!.refreshToken!=null)
    }

    @Test // Test with Mockks
    fun `WHEN performSignUp expect user`() = runTest {
        // GIVEN
        remoteDataSource = mockk()
        repositoryImpl =
            DefaultRepository(remoteDataSource, remoteToLocalMapper)
        coEvery { remoteDataSource.performSignUp(any()) } returns generateJWTResponseUser()

        // WHEN
        val actual = repositoryImpl.performSignUp(SignUpRequestBody("name",
         "email",
         "password",
         "type"))
        // THEN
        assert(actual!!.accessToken!=null)
    }

    @Test // Test with Mockks
    fun `WHEN getOfferById expect one offer with postTime`() = runTest {
        // GIVEN
        remoteDataSource = mockk()
        repositoryImpl =
            DefaultRepository(remoteDataSource, remoteToLocalMapper)
        coEvery { remoteDataSource.getOfferById(any()) } returns generateOfferById("")

        // WHEN
        val actual = repositoryImpl.getOfferById("")

        // THEN
        assert(actual.id=="b2e21a5e-958f-4ab8-84fe-7d78b63b9101")
    }

    @Test // Test with Mockks
    fun `WHEN getOfferList expect one restaurant in result`() = runTest {
        // GIVEN
        remoteDataSource = mockk()
        repositoryImpl =
            DefaultRepository(remoteDataSource, remoteToLocalMapper)
        coEvery { remoteDataSource.getOffers() } returns generateOfferList()

        // WHEN
        val actual = repositoryImpl.getOfferList()

        // THEN
        assert(actual.toList().size==1)
        assert(actual.toList()[0][0].offerName== "2x1")
    }

    @Test // Test with Mockks
    fun `WHEN getRestaurantList expect not empty List with two restaurants`() = runTest {
        // GIVEN
        remoteDataSource = mockk()
        repositoryImpl =
            DefaultRepository(remoteDataSource, remoteToLocalMapper)
        coEvery { remoteDataSource.getRestaurants() } returns generateRestaurantList()

        // WHEN
        val actual = repositoryImpl.getRestaurantList()

        // THEN
        assert(actual.toList().isNotEmpty())
        assert(actual.toList()[0].size==2)

    }
}
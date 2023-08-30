package com.onetoucheasy.restauranteofertas.data

import com.onetoucheasy.restauranteofertas.repository.DefaultRepository
import com.onetoucheasy.restauranteofertas.repository.remote.RemoteDataSource
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultRepositoryTest {
    // SUT
    private lateinit var repositoryImpl: DefaultRepository

    // Dependencies
    private lateinit var remoteDataSource: RemoteDataSource


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN performLogin with invalid data EXPECT error`() = runTest {
        // GIVEN
        //remoteDataSource = mockk()
        //repositoryImpl =
            //DefaultRepository(remoteDataSource)

        //coEvery { remoteDataSource.performLogin(any()) } returns generateErrorResponse401()


        // WHEN
        //val actual = repositoryImpl.performLogin("")

        // THEN
        assert(1==1)

    }

}
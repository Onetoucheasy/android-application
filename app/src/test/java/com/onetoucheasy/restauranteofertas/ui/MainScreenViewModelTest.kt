package com.onetoucheasy.restauranteofertas.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel
import com.onetoucheasy.restauranteofertas.utils.generateUIOffers
import com.onetoucheasy.restauranteofertas.utils.generateUIRestaurants
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var repository: Repository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = MainScreenViewModel(repository)
    }

    @Test
    fun `WHEN getOffers EXPECT successful response with notEmpty List`()  {
        // GIVEN
         coEvery { repository.getOfferList() } returns generateUIOffers()

        // WHEN
         viewModel.getOffers()
         val actualLiveData = viewModel.stateOffers.value

        // THEN
         assert(actualLiveData.isNotEmpty())

    }

    @Test
    fun `WHEN getRestaurants EXPECT successful response with notEmpty List`()  {
        // GIVEN
        coEvery { repository.getRestaurantList() } returns generateUIRestaurants()

        // WHEN
        viewModel.getRestaurants()
        val actualLiveData = viewModel.stateRestaurants.value

        // THEN
        assert(actualLiveData.isNotEmpty())

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
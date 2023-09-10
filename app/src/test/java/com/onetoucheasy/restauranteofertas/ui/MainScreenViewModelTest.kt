package com.onetoucheasy.restauranteofertas.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel
import com.onetoucheasy.restauranteofertas.utils.generateOfferById
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

    @Test
    fun `WHEN getOfferById EXPECT successful response value` () {
        // GIVEN
        val offerId: String = "b2e21a5e-958f-4ab8-84fe-7d78b63b9101"
        coEvery { repository.getOfferById(offerId) } returns generateOfferById(offerId)

        // WHEN
        val result = viewModel.getOfferById(offerId)
        viewModel.getOfferById(offerId)
        val actualLiveData = viewModel.detailState.value

        // THEN
//        assert(result.equals(offerMock3))
        assert(actualLiveData.offerName == "OfferNameMock1-1")
    } // pass 20230910 12:28 CET

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
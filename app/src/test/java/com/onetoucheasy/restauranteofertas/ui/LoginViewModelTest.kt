package com.onetoucheasy.restauranteofertas.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.onetoucheasy.restauranteofertas.repository.Repository
import com.onetoucheasy.restauranteofertas.ui.viewModels.LoginViewModel
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

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: LoginViewModel

    private lateinit var repository: Repository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun `WHEN performLogin with invalid data EXPECT not successful response`()  {
        // GIVEN
        // coEvery { repository.performLogin(id) } returns generateErrorResponse401()

        // WHEN
        // viewModel.performLogin(id, pass)
        // val actualLiveData = viewModel.loginState.getOrAwaitValue()

        // THEN
        // assert(!actualLiveData.favorite)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
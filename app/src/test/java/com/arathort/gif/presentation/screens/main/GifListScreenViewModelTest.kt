package com.arathort.gif.presentation.screens.main

import com.arathort.gif.domain.model.Gif
import com.arathort.gif.domain.usecase.GetTrendingGifsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GifListScreenViewModelTest {

    private val getTrendingGifsUseCase: GetTrendingGifsUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when created, viewmodel loads initial gifs successfully`() = runTest {
        val mockGifs = listOf(mockk<Gif>(), mockk<Gif>())
        coEvery { getTrendingGifsUseCase(offset = 0, limit = 25) } returns Result.success(mockGifs)

        val viewModel = GifListScreenViewModel(getTrendingGifsUseCase)

        advanceUntilIdle()

        val currentState = viewModel.uiState.value
        Assert.assertEquals(false, currentState.isLoading)
        Assert.assertEquals(mockGifs, currentState.gifs)
        Assert.assertEquals(null, currentState.error)
        Assert.assertEquals(true, currentState.isEndReached)
    }

    @Test
    fun `when loaded with IOException, state shows No internet error`() = runTest {
        coEvery { getTrendingGifsUseCase(offset = 0, limit = 25) } returns Result.failure(
            IOException("Network error")
        )

        val viewModel = GifListScreenViewModel(getTrendingGifsUseCase)

        advanceUntilIdle()

        val currentState = viewModel.uiState.value
        Assert.assertEquals(false, currentState.isLoading)
        Assert.assertEquals(true, currentState.gifs.isEmpty())
        Assert.assertEquals("No internet", currentState.error)
    }
}
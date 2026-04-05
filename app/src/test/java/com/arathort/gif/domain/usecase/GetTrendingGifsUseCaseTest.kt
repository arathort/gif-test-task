package com.arathort.gif.domain.usecase

import com.arathort.gif.domain.model.Gif
import com.arathort.gif.domain.repository.GifRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetTrendingGifsUseCaseTest {

    private val repository: GifRepository = mockk()

    private val getTrendingGifsUseCase = GetTrendingGifsUseCase(repository)

    @Test
    fun `invoke returns success result when repository returns gifs`() = runTest {
        val offset = 0
        val limit = 25
        val mockGifs = listOf(
            Gif(
                id = "1",
                title ="Test 1 ",
                thumbnailUrl = "https://example.com/gif1.gif",
                originalUrl = "https://example.com/gif1.gif",
                width = 45,
                height = 45
            ),
            Gif(
                id = "2",
                title ="Test 2 ",
                thumbnailUrl = "https://example.com/gif2.gif",
                originalUrl = "https://example.com/gif2.gif",
                width = 45,
                height = 45
            ),
        )

        coEvery { repository.getTrendingGifs(offset, limit) } returns Result.success(mockGifs)

        val result = getTrendingGifsUseCase(offset, limit)

        assertTrue(result.isSuccess)
        assertEquals(mockGifs, result.getOrNull())

        coVerify(exactly = 1) { repository.getTrendingGifs(offset, limit) }
    }

    @Test
    fun `invoke returns failure result when repository encounters error`() = runTest {
        val offset = 0
        val limit = 25
        val exception = RuntimeException("Network connection lost")

        coEvery { repository.getTrendingGifs(offset, limit) } returns Result.failure(exception)

        val result = getTrendingGifsUseCase(offset, limit)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())

        coVerify(exactly = 1) { repository.getTrendingGifs(offset, limit) }
    }
}
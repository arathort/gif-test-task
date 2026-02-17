package com.arathort.gif.domain.usecase

import com.arathort.gif.domain.model.Gif
import com.arathort.gif.domain.repository.GifRepository
import javax.inject.Inject

class GetTrendingGifsUseCase @Inject constructor(
    private val repository: GifRepository
) {
    suspend operator fun invoke(offset: Int, limit: Int = 25): Result<List<Gif>> {
        return repository.getTrendingGifs(offset, limit)
    }
}
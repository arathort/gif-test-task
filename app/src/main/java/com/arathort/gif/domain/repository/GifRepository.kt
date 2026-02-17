package com.arathort.gif.domain.repository

import com.arathort.gif.domain.model.Gif

interface GifRepository {
    suspend fun getTrendingGifs(offset: Int, limit: Int): Result<List<Gif>>
}
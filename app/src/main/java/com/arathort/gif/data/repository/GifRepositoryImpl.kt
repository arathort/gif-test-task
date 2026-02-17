package com.arathort.gif.data.repository

import com.arathort.gif.data.mapper.toDomain
import com.arathort.gif.data.remote.GiphyApi
import com.arathort.gif.domain.model.Gif
import com.arathort.gif.domain.repository.GifRepository
import javax.inject.Inject

//Я залишив його тут щоб зразу запустився проєкт
private const val API_KEY = "3CpMKv0UFiDoHUWq26Z18dch9KtNsu8a"

class GifRepositoryImpl @Inject constructor(
    private val giphyApi: GiphyApi
): GifRepository {
    override suspend fun getTrendingGifs(
        offset: Int,
        limit: Int
    ): Result<List<Gif>> {
        return try {
            val response = giphyApi.getTrendingGifs(
                apiKey = API_KEY,
                limit = limit,
                offset = offset
            )
            val domainGifs = response.data.map { it.toDomain() }
            Result.success(domainGifs)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}
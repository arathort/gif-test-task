package com.arathort.gif.data.remote

import com.arathort.gif.data.remote.dto.GiphyResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String = "g"
    ): GiphyResponseDto
}
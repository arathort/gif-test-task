package com.arathort.gif.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GiphyResponseDto(
    val data: List<GifDto>
)

@Serializable
data class GifDto(
    val id: String,
    val title: String,
    val username: String = "",
    val images: ImagesDto
)

@Serializable
data class ImagesDto(
    val original: ImageDetailDto,
    @SerialName("fixed_width")
    val fixedWidth: ImageDetailDto
)

@Serializable
data class ImageDetailDto(
    val url: String,
    val width: String,
    val height: String
)

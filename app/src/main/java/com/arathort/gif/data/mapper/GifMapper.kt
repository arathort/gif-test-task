package com.arathort.gif.data.mapper

import com.arathort.gif.data.remote.dto.GifDto
import com.arathort.gif.domain.model.Gif

fun GifDto.toDomain(): Gif {
    return Gif(
        id = this.id,
        title = this.title.ifEmpty { "Untitled Gif" },
        thumbnailUrl = this.images.fixedWidth.url,
        originalUrl = this.images.original.url,
        width = this.images.original.width.toIntOrNull() ?: 0,
        height = this.images.original.height.toIntOrNull() ?: 0
    )
}
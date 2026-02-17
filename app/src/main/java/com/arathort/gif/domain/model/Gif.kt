package com.arathort.gif.domain.model

data class Gif(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val originalUrl: String,
    val width: Int,
    val height: Int
)

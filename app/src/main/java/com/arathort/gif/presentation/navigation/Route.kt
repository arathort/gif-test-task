package com.arathort.gif.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object GifList

@Serializable
data class GifDetails(val title: String, val url: String)
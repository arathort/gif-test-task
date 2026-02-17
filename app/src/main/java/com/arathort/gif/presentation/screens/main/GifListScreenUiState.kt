package com.arathort.gif.presentation.screens.main

import com.arathort.gif.domain.model.Gif

data class GifListScreenUiState(
    val gifs: List<Gif> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isEndReached: Boolean = false
)

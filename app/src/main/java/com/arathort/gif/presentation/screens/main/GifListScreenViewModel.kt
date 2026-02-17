package com.arathort.gif.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arathort.gif.domain.usecase.GetTrendingGifsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GifListScreenViewModel @Inject constructor(
    private val getTrendingGifsUseCase: GetTrendingGifsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GifListScreenUiState())
    val uiState: StateFlow<GifListScreenUiState> = _uiState.asStateFlow()

    private val PAGE_SIZE = 25

    init {
        loadGifs()
    }

    fun loadGifs() {
        val currentState = _uiState.value
        if (currentState.isLoading || currentState.isEndReached) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val currentOffset = _uiState.value.gifs.size

            val result = getTrendingGifsUseCase(offset = currentOffset, limit = PAGE_SIZE)

            result.fold(
                onSuccess = { newGifs ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            gifs = state.gifs + newGifs,
                            isEndReached = newGifs.isEmpty() || newGifs.size < PAGE_SIZE
                        )
                    }
                },
                onFailure = { error ->
                    val userMessage = when (error) {
                        is IOException -> "No internet"

                        else -> error.localizedMessage ?: "Unknown Error"
                    }

                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = userMessage
                        )
                    }
                }
            )
        }
    }
}
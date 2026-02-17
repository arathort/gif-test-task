package com.arathort.gif.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.arathort.gif.presentation.navigation.GifDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GifDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = savedStateHandle.toRoute<GifDetails>()

    private val _uiState =
        MutableStateFlow(GifDetailsScreenUiState(title = args.title, url = args.url))
    val uiState: StateFlow<GifDetailsScreenUiState> = _uiState.asStateFlow()
}
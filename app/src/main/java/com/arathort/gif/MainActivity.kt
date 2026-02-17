package com.arathort.gif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arathort.gif.presentation.navigation.NavigationRoot
import com.arathort.gif.presentation.screens.main.GifListScreen
import com.arathort.gif.ui.theme.GifTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GifTheme {
                NavigationRoot()
            }
        }
    }
}

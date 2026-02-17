package com.arathort.gif.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arathort.gif.presentation.screens.details.GifDetailsScreen
import com.arathort.gif.presentation.screens.main.GifListScreen

@Composable
fun NavigationRoot() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = GifList
    ) {
        composable<GifList> {
            GifListScreen(
                onGifClick = { title, url ->
                    navHostController.navigate(GifDetails(title = title, url = url))
                }
            )
        }
        composable<GifDetails> {
            GifDetailsScreen(
                onBackClick = { navHostController.popBackStack() }
            )
        }

    }
}
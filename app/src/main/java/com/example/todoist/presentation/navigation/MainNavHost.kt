package com.example.todoist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoist.presentation.screen.splash_screen.SplashScreen

import kotlinx.coroutines.delay

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    var loadingCompleted by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(10)
        loadingCompleted = true
    }
    NavHost(
        navController = navController,
        startDestination = SplashScreenNav

    ) {
        composable<SplashScreenNav> {
            SplashScreen(
                isLoading = loadingCompleted,
                onLoadingCompleted = {

                }
            )
        }


    }
}
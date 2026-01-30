package com.example.januaryrecipe.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.januaryrecipe.ui.HomeScreen

@Composable
fun AppNavigation(windowWidthSizeClass: WindowWidthSizeClass) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                windowWidthSizeClass = windowWidthSizeClass,
                homeViewModel = viewModel()
            )
        }
    }
}

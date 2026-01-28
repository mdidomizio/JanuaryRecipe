package com.example.januaryrecipe.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
}
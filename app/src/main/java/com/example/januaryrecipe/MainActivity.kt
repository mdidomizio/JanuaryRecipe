package com.example.januaryrecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.januaryrecipe.navigation.AppNavigation
import com.example.januaryrecipe.ui.theme.JanuaryRecipeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JanuaryRecipeTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                AppNavigation(
                    windowWidthSizeClass =  windowSizeClass.widthSizeClass
                )
            }
        }
    }

}

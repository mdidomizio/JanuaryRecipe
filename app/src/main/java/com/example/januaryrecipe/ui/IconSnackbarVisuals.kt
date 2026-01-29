package com.example.januaryrecipe.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.graphics.vector.ImageVector

class IconSnackbarVisuals(
    override val message: String,
    val icon: ImageVector,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val withDismissAction: Boolean = false
) : SnackbarVisuals

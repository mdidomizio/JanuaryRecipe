package com.example.januaryrecipe.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconSnackbar(
    data: SnackbarData
){
    val visuals: SnackbarVisuals = data.visuals
    val icon = (visuals as? IconSnackbarVisuals)?.icon

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.Black,
        tonalElevation = 6.dp,
        modifier = Modifier
            .padding( 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = visuals.message,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

package com.example.januaryrecipe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.januaryrecipe.R

// Set of Material typography styles to start with
val InstrumentSans = FontFamily(
    Font(R.font.instrument_sans_regular, FontWeight.Normal),
    /*Font(R.font.instrument_sans_medium, FontWeight.Medium),
    Font(R.font.instrument_sans_bold, FontWeight.Bold),
    Font(R.font.instrument_sans_semi_bold, FontWeight.SemiBold),*/
    Font(R.font.instrument_sans_italic, FontWeight.Light)
)

val InstrumentSerif = FontFamily(
    Font(R.font.instrument_serif_regular, FontWeight.Normal),
    Font(R.font.instrument_serif_italic, FontWeight.Normal),
)

val AppTypography = Typography(
    bodyLarge = Typography().bodyLarge.copy(
        fontFamily = InstrumentSans
    ),
    titleLarge = Typography().titleLarge.copy(
        fontFamily = InstrumentSans
    ),
    labelLarge = Typography().labelLarge.copy(
        fontFamily = InstrumentSans
    )
)
/*val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )*/
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

package com.rodoyf.satonda.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rodoyf.satonda.R

val schoolbellFamily = FontFamily(
    Font(R.font.schoolbell_regular, FontWeight.Normal),
)

val chewyFamily = FontFamily(
    Font(R.font.chewy_regular, FontWeight.Normal),
)

val ralewayFamily = FontFamily(
    Font(R.font.raleway_black, FontWeight.ExtraBold),
    Font(R.font.raleway_light, FontWeight.Light),
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
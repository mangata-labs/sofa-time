package com.mangata.core_ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mangata.core_ui.R

val quickSand = FontFamily(
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
    Font(R.font.quicksand_light, FontWeight.Light)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),

    body2 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),

    h1 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),

    h2 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),

    h3 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
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

    h1 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),

    h2 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 23.sp
    ),

    h3 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),

    h4 = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

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


)
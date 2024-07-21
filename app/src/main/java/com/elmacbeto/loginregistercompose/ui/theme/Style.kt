package com.elmacbeto.loginregistercompose.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

object Style {
    val title1 =
        androidx.compose.ui.text.TextStyle(
            color = Blue1,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Start,
            lineHeight = 24.sp
        )

    val title2 =
        androidx.compose.ui.text.TextStyle(
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Start,
            lineHeight = 24.sp
        )

    val title3 =
        androidx.compose.ui.text.TextStyle(
            color = Red1,
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Start,
            lineHeight = 14.sp
        )
    val title4 =
        androidx.compose.ui.text.TextStyle(
            color = Blue1,
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Start,
            lineHeight = 14.sp
        )

    val title5 =
        androidx.compose.ui.text.TextStyle(
            color = Blue1,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Start,
            lineHeight = 24.sp
        )
}

object ButtonStyle {
    val title1 =
        androidx.compose.ui.text.TextStyle(
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Start,
            lineHeight = 24.sp
        )


}
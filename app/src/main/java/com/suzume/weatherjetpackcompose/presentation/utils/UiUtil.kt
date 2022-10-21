package com.suzume.weatherjetpackcompose.presentation.utils

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextWithShadow(
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = Color.White,
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.DarkGray,
                offset = Offset(4.0f, 4.0f),
                blurRadius = 2f
            )
        )
    )
}
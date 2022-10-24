package com.suzume.weatherjetpackcompose.presentation.utils

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

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

@Composable
fun SvgIconLoad(
    modifier: Modifier,
    imageId: String,
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://yastatic.net/weather/i/icons/funky/dark/${imageId}.svg")
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null
    )
}
package com.suzume.weatherjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.suzume.weatherjetpackcompose.R
import com.suzume.weatherjetpackcompose.presentation.ui.theme.WeatherJetpackComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = resources.getColor(R.color.transparent, theme)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        viewModel.loadData("Токио")

        setContent {
            WeatherJetpackComposeTheme {
                BoxWithConstraints(
                    modifier = Modifier
                        .paint(
                            painterResource(id = R.drawable.background_default),
                            contentScale = ContentScale.Crop
                        )
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(this@BoxWithConstraints.maxHeight)
                                    .padding(
                                        top = 16.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 0.dp
                                    )
                            ) {
                                MainScreen(viewModel)
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 0.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 16.dp
                                    )
                            ) {
                                WeekWeatherScreen()
                            }
                        }
                    }
                }
            }
        }
    }

}

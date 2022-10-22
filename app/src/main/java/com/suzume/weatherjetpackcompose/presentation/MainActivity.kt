package com.suzume.weatherjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.suzume.weatherjetpackcompose.App
import com.suzume.weatherjetpackcompose.R
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import com.suzume.weatherjetpackcompose.presentation.ui.theme.WeatherJetpackComposeTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        window.navigationBarColor = resources.getColor(R.color.transparent, theme)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        viewModel.loadCoordinate("Москва")

        setContent {
            WeatherJetpackComposeTheme {

                val weatherState by viewModel.weatherState

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
                        when (weatherState) {
                            is WeatherState.Progress -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            is WeatherState.Error -> {
                                Card(
                                    modifier = Modifier.align(Alignment.Center),
                                    backgroundColor = Color.White.copy(0.2f),
                                    elevation = 0.dp
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .padding(16.dp),
                                        text = (weatherState as WeatherState.Error).message,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                            is WeatherState.ResultValue -> {
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
    }
}

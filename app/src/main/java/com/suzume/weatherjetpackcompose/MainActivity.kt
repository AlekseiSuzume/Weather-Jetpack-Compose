package com.suzume.weatherjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.suzume.weatherjetpackcompose.data.repository.WeatherRepositoryImpl
import com.suzume.weatherjetpackcompose.ui.theme.WeatherJetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repositoryImpl = WeatherRepositoryImpl()

        setContent {
            WeatherJetpackComposeTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Test(repositoryImpl)
                    }
                }
            }
        }
    }
}

@Composable
fun Test(repositoryImpl: WeatherRepositoryImpl) {

    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            scope.launch {
                repositoryImpl.loadWeatherUseCase()
            }
        })
        {
            Text(text = "test download")
        }
    }
}
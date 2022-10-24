package com.suzume.weatherjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.suzume.weatherjetpackcompose.App
import com.suzume.weatherjetpackcompose.R
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

        viewModel.loadCoordinate(DEFAULT_CITY)

        setContent {
            WeatherJetpackComposeTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }

    companion object {

        private const val DEFAULT_CITY = "Москва"

    }

}
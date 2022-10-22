package com.suzume.weatherjetpackcompose

import android.app.Application
import com.suzume.weatherjetpackcompose.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}
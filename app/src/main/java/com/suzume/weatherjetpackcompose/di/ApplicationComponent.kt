package com.suzume.weatherjetpackcompose.di

import android.app.Application
import com.suzume.weatherjetpackcompose.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface ApplicationFactory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent

    }

}
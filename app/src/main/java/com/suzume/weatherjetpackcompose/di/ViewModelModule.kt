package com.suzume.weatherjetpackcompose.di

import androidx.lifecycle.ViewModel
import com.suzume.weatherjetpackcompose.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel

}
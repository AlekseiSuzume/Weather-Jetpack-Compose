package com.suzume.weatherjetpackcompose.domain.util

sealed class CoordinateState{

    class Success(val coordinates: List<String>) :CoordinateState()
    class Error(val message: String) :CoordinateState()

}

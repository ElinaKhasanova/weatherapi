package com.example.xenya.openweather.entities

import com.google.gson.annotations.SerializedName

data class Main(
        @SerializedName("humidity")
        var humidity: Double? = 0.0,
        @SerializedName("pressure")
        var pressure: Double? = 0.0,
        @SerializedName("temp")
        var temp: Double = 0.0
)

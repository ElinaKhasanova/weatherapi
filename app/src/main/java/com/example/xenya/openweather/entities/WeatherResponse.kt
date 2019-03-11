package com.example.xenya.openweather.entities

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
        @SerializedName("cod")
        var code: String? = "",
        @SerializedName("count")
        var count: Int? = 0,
        @SerializedName("list")
        var list: List<City> = listOf(),
        @SerializedName("message")
        var message: String? = "",
        @SerializedName("city")
        var city: CityForecast? = null
)

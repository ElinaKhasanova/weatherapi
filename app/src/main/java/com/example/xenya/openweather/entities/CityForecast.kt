package com.example.xenya.openweather.entities

import com.google.gson.annotations.SerializedName

class CityForecast(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("coord")
        var coord: Coord? = Coord(),
        @SerializedName("country")
        var country: String? = ""
)

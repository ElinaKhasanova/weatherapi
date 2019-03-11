package com.example.xenya.openweather.entities

import com.google.gson.annotations.SerializedName

data class Sys(
        @SerializedName("country")
        var country: String? = ""
)

package com.example.xenya.openweather.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city")
data class City(
        @PrimaryKey
        @SerializedName("id")
        var id: Int = 0,
        @Embedded(prefix = "coord")
        @SerializedName("coord")
        var coord: Coord? = Coord(),
        @SerializedName("dt")
        var dt: Int? = 0,
        @Embedded(prefix = "main")
        @SerializedName("main")
        var main: Main = Main(),
        @SerializedName("name")
        var name: String? = "",
        @Embedded(prefix = "sys")
        @SerializedName("sys")
        var sys: Sys? = Sys(),
        @Embedded(prefix = "wind")
        @SerializedName("wind")
        var wind: Wind? = Wind(),
        @SerializedName("dt_txt")
        var date_time: String? = ""
)

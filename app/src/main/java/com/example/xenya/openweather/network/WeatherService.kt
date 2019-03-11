package com.example.xenya.openweather.network

import com.example.xenya.openweather.entities.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("find?cnt=20&units=metric&appid=afc70c6a9e18dc014eb175d520ea8e59")
    fun findByLocation(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Single<WeatherResponse>

    @GET("forecast?units=metric&appid=afc70c6a9e18dc014eb175d520ea8e59")
    fun findForecastById(@Query("id") cityId: Int): Single<WeatherResponse>
}

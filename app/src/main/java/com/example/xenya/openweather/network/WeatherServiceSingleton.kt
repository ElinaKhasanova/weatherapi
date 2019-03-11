package com.example.xenya.openweather.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WeatherServiceSingleton {
    const val WEATHER_URL = "http://api.openweathermap.org/data/2.5/"

    val weatherService: WeatherService by lazy { getService() }

    private val retrofitInstance: Retrofit by lazy() {
        getRetrofit()
    }

    private fun getService(): WeatherService =
            retrofitInstance
                    .create(WeatherService::class.java)

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

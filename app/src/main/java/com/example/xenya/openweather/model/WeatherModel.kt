package com.example.xenya.openweather.model

import android.location.Location
import com.example.xenya.openweather.database.AppDatabase
import com.example.xenya.openweather.entities.City
import com.example.xenya.openweather.entities.WeatherResponse
import com.example.xenya.openweather.network.WeatherServiceSingleton
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherModel(
        private val appDatabase: AppDatabase
) {
    companion object {
        const val KAZAN_LAT = 55.792115
        const val KAZAN_LON = 49.112339
    }

    fun saveCitiesInDatabase(cities: List<City>) =
            appDatabase.getCityDao().saveAll(cities)

    fun getCitiesFromDb(): Single<List<City>> =
            appDatabase.getCityDao()
                    .getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun getCityFromDbById(cityId: Int): Single<City> =
            appDatabase.getCityDao()
                    .getCityById(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun loadWeatherByLocation(latitude: Double, longitude: Double): Single<List<City>> =
            WeatherServiceSingleton.weatherService
                    .findByLocation(latitude, longitude)
                    .subscribeOn(Schedulers.io())
                    .map { it.list }
                    .map {
                        saveCitiesInDatabase(it)
                        it
                    }
                    .observeOn(AndroidSchedulers.mainThread())

    fun loadForecastById(cityId: Int): Single<WeatherResponse> =
            WeatherServiceSingleton.weatherService
                    .findForecastById(cityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun getKazanLocation(): Location = Location("").apply {
        latitude = KAZAN_LAT
        longitude = KAZAN_LON
    }

    fun getNotNullLocation(location: Location?): Location = location ?: getKazanLocation()
}

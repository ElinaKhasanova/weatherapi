package com.example.xenya.openweather.presenter

import android.location.Location
import androidx.annotation.VisibleForTesting
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.xenya.openweather.model.WeatherModel
import com.example.xenya.openweather.view.MainView
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class MainPresenter(
        private val model: WeatherModel
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        viewState.checkLocationPermission()
    }

    fun onLocationAccessGranted(location: Location?) = loadWeatherByLocation(location)

    fun onLocationAccessNotGranted() = loadWeatherByLocation(null)

    fun onCityClick(cityId: Int) = viewState.navigateToDetailsView(cityId)

    @VisibleForTesting
    fun loadWeatherByLocation(location: Location?) {
        val notNullLocation: Location = model.getNotNullLocation(location)
        model.loadWeatherByLocation(notNullLocation.latitude, notNullLocation.longitude)
                .doOnSubscribe { viewState.showLoading() }
                .doAfterTerminate { viewState.hideLoading() }
                .subscribeBy(onSuccess = {
                    viewState.showCities(it)
                }, onError = {
                    model.getCitiesFromDb().subscribeBy {
                        viewState.showCities(it)
                    }
                    viewState.showError()
                })
    }

}

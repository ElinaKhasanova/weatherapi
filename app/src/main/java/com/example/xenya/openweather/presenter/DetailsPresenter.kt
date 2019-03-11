package com.example.xenya.openweather.presenter

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.xenya.openweather.database.AppDatabase
import com.example.xenya.openweather.model.WeatherModel
import com.example.xenya.openweather.view.DetailsView
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class DetailsPresenter(
        private val cityId: Int,
        private val model: WeatherModel
) : MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        model?.let {
            it.getCityFromDbById(cityId)
                    .subscribeBy(onSuccess = {
                        if (it.sys?.country.isNullOrEmpty()) {
                            it.sys?.country = "DIO"
                        }
                        viewState.showContent(it)
                    }, onError = {
                        viewState.showError()
                    })
        }
    }

    fun onClickButton() = viewState.navigateToForecast(cityId)
}

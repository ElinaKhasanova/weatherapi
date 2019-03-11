package com.example.xenya.openweather.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.xenya.openweather.entities.WeatherResponse

interface ForecastView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideLoading()

    @StateStrategyType(SingleStateStrategy::class)
    fun showForecast(weatherResponse: WeatherResponse)
}

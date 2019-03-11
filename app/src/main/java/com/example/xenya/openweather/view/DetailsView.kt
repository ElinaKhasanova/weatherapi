package com.example.xenya.openweather.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.xenya.openweather.entities.City

interface DetailsView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError()

    @StateStrategyType(SingleStateStrategy::class)
    fun showContent(city: City)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToForecast(cityId: Int)
}

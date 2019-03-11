package com.example.xenya.openweather.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.xenya.openweather.R
import com.example.xenya.openweather.entities.WeatherResponse
import com.example.xenya.openweather.presenter.ForecastPresenter
import com.example.xenya.openweather.view.adapters.ForecastAdapter
import kotlinx.android.synthetic.main.activity_forecast.*

class ForecastActivity : MvpAppCompatActivity(), ForecastView {

    @InjectPresenter
    lateinit var presenter: ForecastPresenter

    @ProvidePresenter
    fun providePresenter(): ForecastPresenter = ForecastPresenter(
            intent.getIntExtra(EXTRA_CITY_ID, 0),
            this
    )

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_loading.visibility = View.GONE
    }

    @SuppressLint("SimpleDateFormat")
    override fun showForecast(weatherResponse: WeatherResponse) {
        rv_weather.layoutManager = LinearLayoutManager(this)
        rv_weather.adapter = ForecastAdapter(weatherResponse.list)
    }

    companion object {
        private const val EXTRA_CITY_ID = "cityid"

        fun getIntent(context: Context, cityId: Int) =
                Intent(context, ForecastActivity::class.java).apply {
                    putExtra(EXTRA_CITY_ID, cityId)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
    }
}

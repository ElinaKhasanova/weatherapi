package com.example.xenya.openweather.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.xenya.openweather.R
import com.example.xenya.openweather.entities.City
import com.example.xenya.openweather.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter = DetailsPresenter(
            intent.getIntExtra(EXTRA_CITY_ID, 0),
            this
    )

    companion object {
        private const val EXTRA_CITY_ID = "cityid"

        fun getIntent(context: Context, cityId: Int) =
                Intent(context, DetailsActivity::class.java).apply {
                    putExtra(EXTRA_CITY_ID, cityId)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        btn_details.setOnClickListener {
            presenter.onClickButton()
        }
    }

    override fun showError() =
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

    override fun showContent(city: City) {
        tv_city.text = "${city.name}, ${city.sys?.country}"
        tv_humidity.text = city.main.humidity.toString()
        tv_pressure.text = city.main.pressure.toString()
        tv_temperature.text = city.main.temp.toString()
        tv_wind.text = city.wind?.speed.toString()
    }

    override fun navigateToForecast(cityId: Int) {
        val intent = ForecastActivity.getIntent(this, cityId)
        startActivity(intent)
    }
}

package com.example.xenya.openweather.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xenya.openweather.R
import com.example.xenya.openweather.entities.City
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.view.*
import kotlinx.android.synthetic.main.item_forecast.*

class ForecastAdapter(val list: List<City>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
            ForecastAdapter.ForecastViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_forecast, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ForecastViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        @SuppressLint("SimpleDateFormat")
        fun bind(weather: City) = with(containerView) {
            tv_date.text = weather.date_time
            val temp = weather.main.temp
            tv_temperature.text = if (temp > 0) {
                "+$temp"
            } else {
                temp.toString()
            }
        }
    }
}

package com.example.xenya.openweather.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xenya.openweather.R
import com.example.xenya.openweather.entities.City
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.view.*

class WeatherListAdapter(
        val list: List<City>,
        val onClick: (Int) -> Unit
) : RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
            WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    class WeatherViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(city: City, onClick: (Int) -> Unit) = with(containerView) {
            tv_city.text = city.name
            tv_country.text = city.sys?.country
            val temp = city.main.temp
            tv_temperature.text = if (temp > 0) {
                "+$temp"
            } else {
                temp.toString()
            }
            setOnClickListener { onClick(city.id) }
        }
    }
}

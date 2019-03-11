package com.example.xenya.openweather

import com.example.xenya.openweather.entities.City
import com.example.xenya.openweather.entities.WeatherResponse
import com.example.xenya.openweather.model.WeatherModel
import com.example.xenya.openweather.presenter.ForecastPresenter
import com.example.xenya.openweather.view.`ForecastView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Spy

class ForecastPresenterTests {
    @Mock
    lateinit var viewStateFor: `ForecastView$$State`

    @Mock
    lateinit var model: WeatherModel

    @Spy
    @InjectMocks
    lateinit var presenter: ForecastPresenter

    @Before
    fun setUp(){
        presenter.setViewState(viewStateFor)
    }

    @Test
    fun whenOnFirstViewAttachIfSuccess(){
        val expectedId = 1
        val mockWeatherResponse = mock(WeatherResponse::class.java)
        doReturn(Single.just(expectedId)).`when`(model).loadForecastById(expectedId)

        presenter.attachView(viewStateFor)

        verify(viewStateFor).showLoading()
        verify(viewStateFor).hideLoading()
        verify(viewStateFor).showForecast(mockWeatherResponse)
    }

    @Test
    fun whenOnFirstViewAttachIfError(){
        val expectedId = 1
//        val mockCity = Mockito.mock(City::class.java)
        doReturn(Single.error<City>(Throwable())).`when`(model).getCityFromDbById(expectedId)

        presenter.attachView(viewStateFor)

        verify(viewStateFor).showError()
    }
}
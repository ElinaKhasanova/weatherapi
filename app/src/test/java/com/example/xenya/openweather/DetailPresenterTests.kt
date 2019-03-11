package com.example.xenya.openweather

import com.example.xenya.openweather.entities.City
import com.example.xenya.openweather.model.WeatherModel
import com.example.xenya.openweather.presenter.DetailsPresenter
import com.example.xenya.openweather.view.`DetailsView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy

class DetailPresenterTests {

    @Mock
    lateinit var viewStateDet: `DetailsView$$State`

    @Mock
    lateinit var model: WeatherModel

    @Spy
    @InjectMocks
    lateinit var presenter: DetailsPresenter

    @Before
    fun setUp(){
        presenter.setViewState(viewStateDet)
    }

    @Test
    fun whenOnFirstViewAttachIfSuccess(){
        val expectedId = 1
        val mockCity = mock(City::class.java)
        doReturn(Single.just(expectedId)).`when`(model).getCityFromDbById(expectedId)

        presenter.attachView(viewStateDet)

        verify(viewStateDet).showContent(mockCity)
    }

    @Test
    fun whenOnFirstViewAttachIfError(){
        val expectedId = 1
        val mockCity = mock(City::class.java)
        doReturn(Single.error<City>(Throwable())).`when`(model).getCityFromDbById(expectedId)

        presenter.attachView(viewStateDet)

        verify(viewStateDet).showError()
    }

    @Test
    fun whenOnClickButton(){
        //val mockId = mock(Int::class.java)
        val expectedId = 1

        presenter.onClickButton()

        verify(viewStateDet).navigateToForecast(expectedId)
    }

}
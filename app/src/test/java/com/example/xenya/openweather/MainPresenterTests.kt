package com.example.xenya.openweather

import android.location.Location
import com.example.xenya.openweather.entities.City
import com.example.xenya.openweather.model.WeatherModel
import com.example.xenya.openweather.presenter.MainPresenter
import com.example.xenya.openweather.view.MainView
import com.example.xenya.openweather.view.`MainView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTests {

    @Mock
    lateinit var viewState: `MainView$$State`

    @Mock
    lateinit var model: WeatherModel

    @Spy
    @InjectMocks
    lateinit var presenter : MainPresenter

    @Before
    fun setUp() {
        presenter.setViewState(viewState)
    }

    @Test
    fun whenAttachViewExpectedSuccess() {
        // Arrange
        val mockView = mock(MainView::class.java)
        // Act
        presenter.attachView(mockView)
        // Assert
        verify(viewState).checkLocationPermission()
    }

    @Test
    fun whenOnLocationAccessGrantedSuccess(){
        //arrange
        val mockLocation = mock(Location::class.java)
        doNothing().`when`(presenter).loadWeatherByLocation(mockLocation)
        //act
        presenter.onLocationAccessGranted(mockLocation)
        //assert
        verify(presenter).loadWeatherByLocation(mockLocation)
    }

    @Test
    fun whenOnLocationAccessNotGrantedSuccess(){
        val mockLocation = mock(Location::class.java)
        doNothing().`when`(presenter).loadWeatherByLocation(mockLocation)

        presenter.onLocationAccessNotGranted()

        verify(presenter).loadWeatherByLocation(mockLocation)
    }

    @Test
    fun whenOnCityClickSuccess(){
        val expectedId = 1
        val mockId = mock(Int::class.java)

        presenter.onCityClick(mockId)

        verify(viewState).navigateToDetailsView(expectedId)
    }

    @Test
    fun whenLoadWeatherByLocationSuccess(){
        val expectedLat = 20.0
        val expectedLon = 25.0
        val mockLocation = mock(Location::class.java)
        val expectedList = ArrayList<City>()
        doReturn(Single.just(expectedList)).`when`(model).loadWeatherByLocation(expectedLat, expectedLon)
        doReturn(mockLocation).`when`(model).getNotNullLocation(mockLocation)
//        doReturn(mockLocation).`when`(model.getNotNullLocation(mockLocation))
        doReturn(expectedLat).`when`(mockLocation).latitude
        doReturn(expectedLon).`when`(mockLocation).longitude

        presenter.loadWeatherByLocation(mockLocation)

        verify(viewState).showLoading()
        verify(viewState).hideLoading()
        verify(viewState).showCities(expectedList)
    }

    @Test
    fun whenLoadWeatherByLocationError(){
        val expectedLat = 20.0
        val expectedLon = 25.0
        val mockLocation = mock(Location::class.java)
        val expectedList = ArrayList<City>()
        doReturn(Single.error<List<City>>(Throwable())).`when`(model).loadWeatherByLocation(expectedLat, expectedLon)

        doReturn(Single.just(expectedList)).`when`(model).getCitiesFromDb()

        doReturn(mockLocation).`when`(model).getNotNullLocation(mockLocation)
//        doReturn(mockLocation).`when`(model.getNotNullLocation(mockLocation))
        doReturn(expectedLat).`when`(mockLocation).latitude
        doReturn(expectedLon).`when`(mockLocation).longitude

        presenter.loadWeatherByLocation(mockLocation)

        verify(model).getCitiesFromDb()
        verify(viewState).showError()
        verify(viewState).showCities(expectedList)
    }


}
package com.example.xenya.openweather.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xenya.openweather.entities.City
import io.reactivex.Single

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(list: List<City>)

    @Query("SELECT * FROM city")
    fun getAll(): Single<List<City>>

    @Query("SELECT * FROM city WHERE id = :id")
    fun getCityById(id: Int): Single<City>
}

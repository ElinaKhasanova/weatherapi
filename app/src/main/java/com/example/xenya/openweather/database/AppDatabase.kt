package com.example.xenya.openweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xenya.openweather.entities.City

@Database(entities = [City::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "weather"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                instance ?: buildDatabase(context.applicationContext).also { instance = it }

        private fun buildDatabase(context: Context): AppDatabase =
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }

    abstract fun getCityDao(): CityDao
}

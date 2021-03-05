package ru.svlit.sandbox.feature.weather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData

@Database(
    entities = [CurrentWeatherData::class],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {

        @Volatile
        private var INSTANCE: ForecastDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context): ForecastDatabase = INSTANCE ?: synchronized(LOCK) {
            return INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): ForecastDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java,
                "forecast.db"
            ).build()
        }
    }
}
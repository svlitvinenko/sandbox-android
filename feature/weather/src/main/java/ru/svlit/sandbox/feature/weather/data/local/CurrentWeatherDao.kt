package ru.svlit.sandbox.feature.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData
import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData.Companion.CURRENT_WEATHER_ID

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = REPLACE)
    suspend fun upsert(data: CurrentWeatherData)

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    suspend fun get(): CurrentWeatherData?
}
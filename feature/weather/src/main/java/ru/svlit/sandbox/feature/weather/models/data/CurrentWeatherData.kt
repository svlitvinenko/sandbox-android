package ru.svlit.sandbox.feature.weather.models.data


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "current_weather")
data class CurrentWeatherData(
    @SerializedName("request")
    @Embedded(prefix = "request_")
    val requestDescription: RequestDescription,

    @SerializedName("location")
    @Embedded(prefix = "location_")
    val location: Location,

    @SerializedName("current")
    @Embedded(prefix = "weather_")
    val currentWeather: CurrentWeather
) {

    data class RequestDescription(
        @SerializedName("type") val type: String,
        @SerializedName("query") val query: String,
        @SerializedName("language") val language: String,
        @SerializedName("unit") val unit: String
    )

    data class Location(
        @SerializedName("name") val name: String,
        @SerializedName("country") val country: String,
        @SerializedName("region") val region: String,
        @SerializedName("lat") val latitude: String,
        @SerializedName("lon") val longitude: String,
        @SerializedName("timezone_id") val timezoneId: String,
        @SerializedName("localtime") val localtime: String,
        @SerializedName("localtime_epoch") val localtimeEpoch: Int,
        @SerializedName("utc_offset") val utcOffset: String
    )

    data class CurrentWeather(
        @SerializedName("observation_time") val observationTime: String,
        @SerializedName("temperature") val temperature: Float,
        @SerializedName("weather_code") val weatherCode: Int,
        @SerializedName("weather_icons") val weatherIcons: List<String>,
        @SerializedName("weather_descriptions") val weatherDescriptions: List<String>,
        @SerializedName("wind_speed") val windSpeed: Int,
        @SerializedName("wind_degree") val windDegree: Int,
        @SerializedName("wind_dir") val windDir: String,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("precip") val precipitation: Double,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("cloudcover") val cloudCover: Int,
        @SerializedName("feelslike") val feelsLike: Int,
        @SerializedName("uv_index") val uvIndex: Int,
        @SerializedName("visibility") val visibility: Int,
        @SerializedName("is_day") val isDay: String
    )

    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID

    companion object {
        const val CURRENT_WEATHER_ID = 0
    }
}

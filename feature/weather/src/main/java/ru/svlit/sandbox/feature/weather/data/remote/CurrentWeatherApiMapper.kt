package ru.svlit.sandbox.feature.weather.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.svlit.sandbox.core.network.data.ConnectivityInterceptor
import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData

interface CurrentWeatherApiMapper {

    @GET("current")
    suspend fun getCurrentWeather(
        @Query("query") location: String
    ): CurrentWeatherData

    companion object {
        private const val BASE_URL = "http://api.weatherstack.com/"
        private const val ACCESS_KEY_NAME = "access_key"
        private const val ACCESS_KEY_VALUE = "ca262368b0cad9de8fbbcde1c23c905d"

        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): CurrentWeatherApiMapper {
            val requestInterceptor = Interceptor { chain: Chain ->
                val url: HttpUrl = chain.request().url
                    .newBuilder()
                    .addQueryParameter(ACCESS_KEY_NAME, ACCESS_KEY_VALUE)
                    .build()

                val request = chain.request().newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) })
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrentWeatherApiMapper::class.java)
        }
    }
}

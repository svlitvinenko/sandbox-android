package ru.svlit.sandbox.feature.weather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.Item
import ru.svlit.sandbox.core.designsystem.item.adapter.NavigationEvent
import ru.svlit.sandbox.core.designsystem.item.library.carousel.Carousel
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString.BodyStyleType.SECONDARY
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString.TitleStyleType.HEADLINE
import ru.svlit.sandbox.core.designsystem.item.library.info.Info
import ru.svlit.sandbox.feature.weather.data.CurrentWeatherRepository
import ru.svlit.sandbox.feature.weather.models.domain.Weather
import ru.svlit.sandbox.feature.weather.models.presentation.WeatherExpanded

/**
 * Модель представления экрана "Погода".
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class ForecastViewModel(
    private val currentWeatherRepository: CurrentWeatherRepository
) : ViewModel(), EventListener {

    private val contentMutableState = MutableStateFlow<List<Item>>(listOf())
    val contentState: StateFlow<List<Item>> = contentMutableState

    private val navigationEventChannel = Channel<NavigationEvent>()
    val navigationEventFlow: Flow<NavigationEvent> = navigationEventChannel.receiveAsFlow()

    private val currentWeatherState: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    private val recommendationsState: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())

    fun initialize() {
        viewModelScope.launch {
            contentMutableState.value = listOf(
                Info(
                    id = "loading",
                    title = ru.svlit.sandbox.core.models.TextWrapper.ByString("Погода"),
                    subtitle = ru.svlit.sandbox.core.models.TextWrapper.ByString("Пожалуйста, подождите...")
                )
            )
            val currentWeather: Weather = currentWeatherRepository.getCurrentWeather("Podolsk", isForce = true)
            currentWeatherState.value = listOf(
                ReadonlyString(
                    id = "title_weather",
                    title = ru.svlit.sandbox.core.models.TextWrapper.ByString("Погода сейчас"),
                    titleStyleType = HEADLINE,
                    body = ru.svlit.sandbox.core.models.TextWrapper.ByString(""),
                    bodyStyleType = SECONDARY
                ),
                WeatherExpanded(
                    id = "current_${currentWeather.region}",
                    region = currentWeather.region,
                    temperature = currentWeather.temperature
                )
            )
            recommendationsState.value = listOf(
                ReadonlyString(
                    id = "title_recommendations",
                    title = ru.svlit.sandbox.core.models.TextWrapper.ByString("Рекомендуем"),
                    titleStyleType = HEADLINE,
                    body = ru.svlit.sandbox.core.models.TextWrapper.ByString(""),
                    bodyStyleType = SECONDARY
                ),
                Carousel(
                    id = "shortcuts",
                    items = listOf()
                )
            )
            updateContent()
        }
    }

    private fun updateContent() {
        val recommendations: List<Item> = recommendationsState.value
        val weather: List<Item> = currentWeatherState.value
        contentMutableState.value = recommendations + weather
    }

    override fun onEvent(event: Event) {
        Log.d(TAG, "Новое событие: $event")
        when (event) {
            is NavigationEvent -> onNavigationEvent(event)
            else -> {
                Log.w(TAG, "Событие не было обработано: $event")
            }
        }
    }

    private fun onNavigationEvent(event: NavigationEvent) {
        viewModelScope.launch { navigationEventChannel.send(event) }
    }

    private companion object {
        private const val TAG = "CurrentWeatherViewModel"
    }
}
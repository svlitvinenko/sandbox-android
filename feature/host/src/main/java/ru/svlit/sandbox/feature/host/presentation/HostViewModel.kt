package ru.svlit.sandbox.feature.host.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.NavigationEvent
import ru.svlit.sandbox.core.designsystem.item.library.carousel.Carousel
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString.BodyStyleType.SECONDARY
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString.TitleStyleType.HEADLINE
import ru.svlit.sandbox.core.models.TextWrapper.ByString
import ru.svlit.sandbox.feature.nba.data.GetNbaFeaturesRepository
import ru.svlit.sandbox.feature.nba.data.NbaCreator
import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData

@FlowPreview
@ExperimentalCoroutinesApi
class HostViewModel(
    private val getNbaFeaturesRepository: GetNbaFeaturesRepository,
    private val nbaCreators: Set<NbaCreator<*>>
) : ViewModel(), EventListener {

    private val contentMutableStateFlow: MutableStateFlow<HostContent> = MutableStateFlow(HostContent(emptyList(), emptyList(), emptyList()))
    val contentStateFlow: StateFlow<HostContent> = contentMutableStateFlow

    private val navigationEventChannel = Channel<NavigationEvent>()
    val navigationEventFlow: Flow<NavigationEvent> = navigationEventChannel.receiveAsFlow()

    fun initialize() = viewModelScope.launch {
        contentMutableStateFlow.value = HostContent(
            topItems = listOf(
                ReadonlyString(
                    id = "title_recommendations",
                    title = ByString("На основе ваших действий"),
                    titleStyleType = HEADLINE,
                    body = ByString("Удобные операции"),
                    bodyStyleType = SECONDARY
                ),
            ),
            mainItems = emptyList(),
            bottomItems = emptyList()
        )

        val featureData: List<NbaFeatureData> = getNbaFeaturesRepository.getFeatures()

        val oldContent = contentMutableStateFlow.value
        contentMutableStateFlow.value = oldContent.copy(
            topItems = listOf(
                ReadonlyString(
                    id = "title_recommendations",
                    title = ByString("Привет, Сергей!"),
                    titleStyleType = HEADLINE,
                    body = ByString("Лучшее для вас"),
                    bodyStyleType = SECONDARY
                ),
                Carousel(
                    id = "recommendations",
                    items = featureData.map { nbaData: NbaFeatureData ->
                        nbaCreators.first { it.nbaDataClass == nbaData.javaClass }
                            .create(nbaData)
                    }
                )
            )
        )
    }

    override fun onEvent(event: Event) {
        when (event) {
            is NavigationEvent -> onNavigationEvent(event)
            else -> Log.w(TAG, "Событие не было обработано: $event")
        }
    }

    private fun onNavigationEvent(event: NavigationEvent) {
        viewModelScope.launch { navigationEventChannel.send(event) }
    }

    private companion object {
        private const val TAG = "HostViewModel"
    }
}

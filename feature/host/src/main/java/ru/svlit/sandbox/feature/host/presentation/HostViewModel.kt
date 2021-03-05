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
import ru.svlit.sandbox.core.base.entry.EntryPoint
import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.NavigationEvent
import ru.svlit.sandbox.core.designsystem.item.library.card.square.SquareCard
import ru.svlit.sandbox.core.designsystem.item.library.carousel.Carousel
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString
import ru.svlit.sandbox.core.models.TextWrapper
import ru.svlit.sandbox.core.models.TextWrapper.ByString
import ru.svlit.sandbox.feature.host.presentation.event.LaunchEntryPointEvent

@FlowPreview
@ExperimentalCoroutinesApi
class HostViewModel(
    private val entryPoints: Set<EntryPoint<*>>
) : ViewModel(), EventListener {

    private val contentMutableStateFlow: MutableStateFlow<HostContent> = MutableStateFlow(HostContent(emptyList(), emptyList(), emptyList()))
    val contentStateFlow: StateFlow<HostContent> = contentMutableStateFlow

    private val navigationEventChannel = Channel<NavigationEvent>()
    val navigationEventFlow: Flow<NavigationEvent> = navigationEventChannel.receiveAsFlow()

    fun initialize() {
        contentMutableStateFlow.value = HostContent(
            topItems = listOf(
                ReadonlyString(
                    id = "title_recommendations",
                    title = ByString("На основе ваших действий"),
                    titleStyleType = ReadonlyString.TitleStyleType.HEADLINE,
                    body = ByString("Удобные операции"),
                    bodyStyleType = ReadonlyString.BodyStyleType.SECONDARY
                ),
                Carousel(
                    id = "recommendations",
                    items = entryPoints.filter { it.isEnabled }.map { entryPoint: EntryPoint<*> ->
                        SquareCard(
                            id = entryPoint.javaClass.simpleName,
                            text = TextWrapper("Некая фича"),
                            onClickEvent = LaunchEntryPointEvent(entryPoint)
                        )
                    }
                )
            ),
            mainItems = emptyList(),
            bottomItems = emptyList()
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

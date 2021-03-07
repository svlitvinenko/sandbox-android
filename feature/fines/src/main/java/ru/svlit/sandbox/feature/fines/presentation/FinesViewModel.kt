package ru.svlit.sandbox.feature.fines.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.svlit.sandbox.core.designsystem.item.adapter.*
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.library.animated.AnimatedInfo
import ru.svlit.sandbox.core.designsystem.item.library.animated.AnimationWrapper
import ru.svlit.sandbox.core.designsystem.item.library.button.ButtonItem
import ru.svlit.sandbox.core.designsystem.item.library.button.ConfirmEvent
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString.BodyStyleType.SECONDARY
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyString.TitleStyleType.BODY_1
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ValidationStrategy.HARD
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ValidationStrategy.SOFT
import ru.svlit.sandbox.core.designsystem.item.library.readonly.string.EditableString
import ru.svlit.sandbox.core.designsystem.item.library.readonly.string.EditableStringValueChangedEvent
import ru.svlit.sandbox.core.models.TextWrapper.*
import ru.svlit.sandbox.feature.fines.R
import java.util.*

/**
 * Модель представления штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class FinesViewModel : ViewModel(), EventListener {

    private val contentMutableState: MutableStateFlow<FinesContent> =
        MutableStateFlow(FinesContent(ByString(""), emptyList(), emptyList()))
    val contentState: StateFlow<FinesContent> = contentMutableState

    private val navigationEventChannel = Channel<NavigationEvent>()
    val navigationEventFlow: Flow<NavigationEvent> = navigationEventChannel.receiveAsFlow()

    fun initialize(arguments: FinesArguments) {
        viewModelScope.launch {
            contentMutableState.value = FinesContent(
                title = ByString("Штрафы"),
                mainItems = listOf(
                    AnimatedInfo(
                        id = "loading_info",
                        title = ByString("Штрафы ГИБДД"),
                        subtitle = ByPlural(R.plurals.number_of_fines, arguments.count, arguments.count.toString()),
                        animation = AnimationWrapper.ByResource(R.raw.anim_driver_license)
                    )
                ),
                bottomItems = listOf()
            )
            delay(2000L)
            contentMutableState.value = FinesContent(
                title = ByString("Штрафы"),
                mainItems = listOf(
                    EditableString(
                        id = "vu",
                        value = "",
                        title = "Водительское удостоверение",
                        error = Optional.empty(),
                        onValueChangedEventSupplier = { EditableStringValueChangedEvent("vu", it) },
                        modifiers = listOf(),
                        validators = listOf(),
                        validationStrategy = SOFT
                    ),
                    ReadonlyString(
                        id = "sts_and_grz_description_1",
                        title = ByString("Штрафы чаще оформляют на машину"),
                        titleStyleType = BODY_1,
                        body = ByString("Заполните следующие поля, чтобы получить более точные данные."),
                        bodyStyleType = SECONDARY
                    ),
                    EditableString(
                        id = "sts",
                        value = "",
                        title = "Свидетельство о регистрации ТС",
                        error = Optional.empty(),
                        onValueChangedEventSupplier = { value: String -> EditableStringValueChangedEvent("sts", value) },
                        modifiers = listOf(),
                        validators = listOf(),
                        validationStrategy = SOFT
                    ),
                    EditableString(
                        id = "grz",
                        value = "",
                        title = "Госномер машины",
                        error = Optional.empty(),
                        onValueChangedEventSupplier = { EditableStringValueChangedEvent("grz", it) },
                        modifiers = listOf(),
                        validators = listOf(),
                        validationStrategy = SOFT
                    )
                ),
                bottomItems = listOf(
                    ButtonItem(
                        id = "confirm_button",
                        text = ByResource(R.string.action_search_fines),
                        onClickEvent = ConfirmEvent
                    )
                )
            )
        }
    }

    override fun onEvent(event: Event) {
        Log.d(TAG, "Новое событие: $event")
        when (event) {
            is EditableStringValueChangedEvent -> onValueChanged(event.id, event.value)
            is ConfirmEvent -> onConfirm()
            is Back -> onBackPressed()
        }
    }

    private fun onBackPressed() {
        viewModelScope.launch { navigationEventChannel.send(FinishEvent) }
    }

    private fun onConfirm() {
        enableHardValidation()
        val isInputValid: Boolean = isInputValid()
        if (isInputValid) {
            val values: Map<String, String> = getValues()
            val pairsForLog: String = values.entries.joinToString(separator = "\n") { "\t${it.key} = ${it.value}" }
            Log.d(TAG, "Подтверждены значения: \n$pairsForLog")
            viewModelScope.launch { navigationEventChannel.send(FinishEvent) }
        }
    }

    private fun getValues(): Map<String, String> {
        val content = contentMutableState.value
        return content.mainItems.filterIsInstance<EditableString>().map { it.id to it.value }.toMap()
    }

    private fun enableHardValidation() {
        val oldContent = contentMutableState.value
        contentMutableState.value = oldContent.copy(
            mainItems = oldContent.mainItems.map {
                if (it is EditableString && it.validationStrategy == SOFT) {
                    it.copy(validationStrategy = HARD)
                } else {
                    it
                }
            }
        )
    }

    private fun isInputValid(): Boolean {
        val content = contentMutableState.value
        return content.mainItems.filterIsInstance<EditableString>().all { it.validate().isSuccessful }
    }

    private fun onValueChanged(id: String, value: String) {
        val oldContent = contentMutableState.value
        contentMutableState.value = oldContent.copy(
            mainItems = oldContent.mainItems.map {
                if (it is EditableString && it.id == id) {
                    it.copy(value = value)
                } else {
                    it
                }
            }
        )
    }

    private companion object {
        private const val TAG = "AddServiceViewModel"
    }
}

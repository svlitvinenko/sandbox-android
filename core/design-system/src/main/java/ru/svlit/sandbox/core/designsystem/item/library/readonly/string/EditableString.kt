package ru.svlit.sandbox.core.designsystem.item.library.readonly.string

import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.Item
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ValidationStrategy
import ru.svlit.sandbox.core.models.Modifier
import ru.svlit.sandbox.core.models.OperationResult
import ru.svlit.sandbox.core.models.OperationResult.Companion.success
import ru.svlit.sandbox.core.models.TextWrapper
import ru.svlit.sandbox.core.models.Validator
import java.util.*

/**
 * Элемент презентационного слоя "Редактируемая строка".
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
data class EditableString(
    override val id: String,
    val value: String,
    val title: String,
    val error: Optional<String>,
    val onValueChangedEventSupplier: (validatedValue: String) -> Event,
    val modifiers: List<Modifier>,
    val validators: List<Validator<String>>,
    val validationStrategy: ValidationStrategy
) : Item {

    fun validate(): OperationResult<Unit, TextWrapper> {
        return validators.asSequence()
            .map { it.validate(value) }
            .firstOrNull { !it.isSuccessful }
            ?: success()
    }
}

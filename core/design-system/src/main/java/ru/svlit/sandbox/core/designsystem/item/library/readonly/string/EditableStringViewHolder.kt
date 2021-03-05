package ru.svlit.sandbox.core.designsystem.item.library.readonly.string

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ValidationStrategy
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ValidationStrategy.HARD
import ru.svlit.sandbox.core.models.TextWrapper
import java.util.*

/**
 * [ItemViewHolder], отрисовывающий [EditableString].
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class EditableStringViewHolder(
    itemView: View,
    private val eventListener: EventListener
) : ItemViewHolder<EditableString>(itemView) {

    private val textInputLayout: TextInputLayout = itemView as TextInputLayout
    private val editText: EditText = itemView.findViewById(R.id.edit_text)
    private var item: Optional<EditableString> = Optional.empty()

    private val textWatcher: TextWatcher = object : TextWatcher {

        var textBeforeChange: String? = null

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            textBeforeChange = s.toString()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Намеренно оставлен пустым.
        }

        override fun afterTextChanged(textAfterChange: Editable) {
            onTextChanged(textBeforeChange.orEmpty(), textAfterChange)
        }
    }

    init {
        editText.addTextChangedListener(textWatcher)
    }

    override fun bind(item: EditableString) {
        this.item = Optional.of(item)
        editText.setText(item.value)
        textInputLayout.hint = item.title
    }

    override fun update(oldItem: EditableString, newItem: EditableString) {
        item = Optional.of(newItem)
        if (newItem.value != editText.text.toString()) {
            editText.setText(newItem.value)
        }

        if (newItem.validationStrategy == HARD) {
            validate(item.get())
        }

        if (oldItem.title != newItem.title) {
            textInputLayout.hint = newItem.title
        }
    }

    private fun onTextChanged(oldValue: String, newValue: Editable) {
        item.ifPresent { item ->
            editText.removeTextChangedListener(textWatcher)
            var textBeforeModify: String = oldValue
            item.modifiers.forEach {
                it.modify(textBeforeModify, newValue)
                textBeforeModify = newValue.toString()
            }
            val value: String = newValue.toString()
            validate(item)
            editText.addTextChangedListener(textWatcher)
            val event: Event = item.onValueChangedEventSupplier.invoke(value)
            eventListener.onEvent(event)
        }
    }

    private fun validate(item: EditableString) {
        item.validate().get(
            { onInputValid() },
            { onInputInvalid(item.validationStrategy, it) }
        )
    }

    private fun onInputValid() {
        textInputLayout.error = null
    }

    private fun onInputInvalid(validationStrategy: ValidationStrategy, errorTextWrapper: TextWrapper) {
        if (validationStrategy == HARD) {
            textInputLayout.error = errorTextWrapper.getText(itemView.context)
        }
    }
}

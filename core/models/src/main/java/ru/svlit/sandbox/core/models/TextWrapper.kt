package ru.svlit.sandbox.core.models

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

/**
 * Обёртка над строкой, которая позволяет одинаково работать как со строками, так и со строковыми ресурсами.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
sealed class TextWrapper : Parcelable {

    companion object {
        operator fun invoke(@StringRes resource: Int, formatArgs: List<String> = listOf()): TextWrapper {
            return ByResource(resource, formatArgs)
        }

        operator fun invoke(value: String, formatArgs: List<String> = listOf()): TextWrapper {
            return ByString(value, formatArgs)
        }
    }

    /**
     * Извлекает строковое значение, используя [context].
     */
    abstract fun getText(context: Context): String

    /**
     * Строковый ресурс.
     *
     * @property resource ресурс.
     */
    @Parcelize
    data class ByResource(@StringRes private val resource: Int, private val formatArgs: List<String> = listOf()) : TextWrapper() {
        override fun getText(context: Context): String = context.getString(resource, formatArgs)
    }

    /**
     * Строка.
     *
     * @property value значение.
     */
    @Parcelize
    data class ByString(val value: String, private val formatArgs: List<String> = listOf()) : TextWrapper() {
        override fun getText(context: Context): String = String.format(value, formatArgs)
    }
}

package ru.svlit.sandbox.core.models

import android.text.Editable
import java.util.*

/**
 * Модификатор значения.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
abstract class Modifier {

    /**
     * Модифицирует значение. Создаёт **новое** значение, если старое понадоблось изменить.
     *
     * Если значение не нужно изменять, возвращает [Optional.empty].
     */
    fun modify(oldValue: String, newValue: Editable) {
        doModify(oldValue, newValue)
    }

    /**
     * Выполняет модификацию значения на основе старого значения [initialValue] и попытки установить новое значение [newValue].
     *
     * Если модификация действительно нужна, должен вернуть непустой [Optional] с модифицированным значением.
     */
    protected abstract fun doModify(initialValue: String, newValue: Editable)
}

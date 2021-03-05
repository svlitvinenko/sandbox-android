package ru.svlit.sandbox.core.models

import ru.svlit.sandbox.core.models.OperationResult.Companion.failure
import ru.svlit.sandbox.core.models.OperationResult.Companion.success


/**
 * Валидатор.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
abstract class Validator<T : Any> {

    protected abstract val errorMessage: TextWrapper

    /**
     * Валидирует значение [value]. Если валидация неуспешна, возвращает [TextWrapper] с ошибкой.
     *
     * Валидировать нужно, не модифицируя перед этим [value].
     */
    fun validate(value: T): OperationResult<Unit, TextWrapper> {
        return if (isValid(value)) {
            success()
        } else {
            failure(errorMessage)
        }
    }

    abstract fun isValid(value: T): Boolean
}
